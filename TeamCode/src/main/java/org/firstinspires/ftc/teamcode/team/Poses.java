package org.firstinspires.ftc.teamcode.team;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

/**
 * Every named spot on the field. Students edit numbers HERE and nowhere else.
 *
 * FRAME: the same one the Limelight uses. Origin is the CENTER of the field, units are
 * INCHES, and heading is in DEGREES (the factory converts to radians for you).
 * Using one frame everywhere means there is no conversion step to get wrong.
 *
 * The numbers below are EXAMPLES so the project compiles. Replace them by driving the robot
 * to each spot and reading the pose off the telemetry in TeleOpMain.
 */
public final class Poses {
    private Poses() {}

    private static final PoseFactory pose = PoseFactory.degrees();

    //                                         x (in)  y (in)  heading (deg)
    public static final Pose START  = pose.of(-60,    -36,     0);
    public static final Pose SCORE  = pose.of(-36,    -12,    45);
    public static final Pose PICKUP = pose.of(-48,     36,    90);
    public static final Pose PARK   = pose.of(-60,     48,     0);
}
