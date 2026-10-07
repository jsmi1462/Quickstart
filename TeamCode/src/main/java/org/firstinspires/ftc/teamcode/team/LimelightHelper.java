package org.firstinspires.ftc.teamcode.team;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

/**
 * Wraps the Limelight 3A so students only ever call relocalize().
 *
 * Uses MegaTag2: we tell the Limelight which way the robot is facing (from odometry) and it
 * returns an AprilTag-based field position. Its field frame (origin at field center, meters)
 * is the same frame as Poses, so the only conversion is meters to inches.
 *
 * SETUP (mentor, once): in the Limelight web UI, load the AprilTag pipeline for the current
 * game's field map, and set the camera position on the robot. In the Robot Configuration, name
 * the device "limelight".
 *
 * Call relocalize() while the robot is STOPPED. The camera image is a few tens of milliseconds
 * old, so a moving robot gets a slightly wrong correction.
 */
public class LimelightHelper {
    /** Ignore results older than this many milliseconds. */
    public static double MAX_STALENESS_MS = 100;

    private final Limelight3A limelight;

    public LimelightHelper(HardwareMap hardwareMap) {
        this(hardwareMap, 0);
    }

    public LimelightHelper(HardwareMap hardwareMap, int pipeline) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(pipeline);
        limelight.start();
    }

    /**
     * Field pose from the camera, in the Poses frame (inches, heading in radians),
     * or null when no usable tag is in view.
     *
     * @param headingRadians the robot's current heading from odometry
     */
    public Pose poseOrNull(double headingRadians) {
        limelight.updateRobotOrientation(Math.toDegrees(headingRadians));
        LLResult result = limelight.getLatestResult();
        if (result == null || !result.isValid() || result.getStaleness() > MAX_STALENESS_MS) {
            return null;
        }
        Pose3D botpose = result.getBotpose_MT2();
        if (botpose == null) {
            return null;
        }
        double xInches = botpose.getPosition().toUnit(DistanceUnit.INCH).x;
        double yInches = botpose.getPosition().toUnit(DistanceUnit.INCH).y;
        return new Pose(xInches, yInches, headingRadians);
    }

    /**
     * The one call students use. Snaps the follower's position to the camera's position.
     * Heading is kept from odometry.
     *
     * @return true if a correction was applied
     */
    public boolean relocalize(Follower follower) {
        Pose fixed = poseOrNull(follower.pose().heading());
        if (fixed == null) {
            return false;
        }
        follower.setPose(fixed);
        return true;
    }

    public void stop() {
        limelight.stop();
    }
}
