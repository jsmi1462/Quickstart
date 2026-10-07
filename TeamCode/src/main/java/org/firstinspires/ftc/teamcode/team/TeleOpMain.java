package org.firstinspires.ftc.teamcode.team;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

/**
 * Basic driving plus live pose readout. Drive the robot to a spot, read the pose from the
 * telemetry, and type those numbers into Poses.java.
 *
 * Add mechanism controls for your robot below the drive line.
 *
 * If a direction feels backwards (forward, strafe, or turn), flip the sign on that stick.
 */
@TeleOp(name = "TeleOp Main", group = "Team")
public class TeleOpMain extends LinearOpMode {

    @Override
    public void runOpMode() {
        Follower follower = Constants.create(hardwareMap);
        LimelightHelper limelight = new LimelightHelper(hardwareMap);

        follower.setPose(Poses.START);

        waitForStart();

        while (opModeIsActive()) {
            follower.update();

            follower.manual(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);

            // TODO: mechanism controls go here

            if (gamepad1.a) {
                limelight.relocalize(follower); // hold A while stopped to snap to the camera
            }

            telemetry.addData("pose", follower.pose());
            telemetry.addLine("Hold A while stopped to correct position from AprilTags");
            telemetry.update();
        }

        limelight.stop();
    }
}
