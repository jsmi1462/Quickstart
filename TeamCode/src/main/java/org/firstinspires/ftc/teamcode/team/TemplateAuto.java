package org.firstinspires.ftc.teamcode.team;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedro.Constants;

/**
 * Template autonomous written as a state machine. Copy a case, change the route or the action.
 *
 * Pattern for every step:
 *   - "drive" case: start a route once (the `entering` flag), then wait until the follower is done.
 *   - "action" case: run a mechanism, wait for a timer, then move on.
 * The follower must be updated every loop. That is the follower.update() at the top.
 */
@Autonomous(name = "Template Auto", group = "Team")
public class TemplateAuto extends LinearOpMode {

    private Follower follower;
    private LimelightHelper limelight;
    private final ElapsedTime timer = new ElapsedTime();

    private int state = 0;
    private boolean entering = true;

    private void next() {
        state++;
        entering = true;
        timer.reset();
    }

    @Override
    public void runOpMode() {
        follower = Constants.create(hardwareMap);
        limelight = new LimelightHelper(hardwareMap);

        follower.setPose(Poses.START);
        telemetry.addLine("Ready. Robot must be placed on Poses.START.");
        telemetry.update();

        waitForStart();
        timer.reset();

        while (opModeIsActive()) {
            follower.update();

            switch (state) {
                case 0: // drive to the scoring spot
                    if (entering) {
                        follower.follow(Routes.startToScore());
                        entering = false;
                    }
                    if (!follower.isBusy()) {
                        limelight.relocalize(follower); // fix any odometry drift while stopped
                        next();
                    }
                    break;

                case 1: // score (replace the timer with your mechanism)
                    // TODO: run your scoring mechanism here
                    if (timer.seconds() > 1.0) {
                        next();
                    }
                    break;

                case 2: // drive to pickup
                    if (entering) {
                        follower.follow(Routes.scoreToPickup());
                        entering = false;
                    }
                    if (!follower.isBusy()) {
                        next();
                    }
                    break;

                case 3: // pick up (replace the timer with your mechanism)
                    // TODO: run your intake mechanism here
                    if (timer.seconds() > 1.0) {
                        next();
                    }
                    break;

                case 4: // back to score
                    if (entering) {
                        follower.follow(Routes.pickupToScore());
                        entering = false;
                    }
                    if (!follower.isBusy()) {
                        limelight.relocalize(follower);
                        next();
                    }
                    break;

                case 5: // score again
                    // TODO: run your scoring mechanism here
                    if (timer.seconds() > 1.0) {
                        next();
                    }
                    break;

                case 6: // park
                    if (entering) {
                        follower.follow(Routes.scoreToPark());
                        entering = false;
                    }
                    if (!follower.isBusy()) {
                        next();
                    }
                    break;

                default: // done
                    break;
            }

            telemetry.addData("state", state);
            telemetry.addData("pose", follower.pose());
            telemetry.update();
        }

        limelight.stop();
    }
}
