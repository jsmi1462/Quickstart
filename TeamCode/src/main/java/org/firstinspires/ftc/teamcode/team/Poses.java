package org.firstinspires.ftc.teamcode.team;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

/**
 * Named spots on the field.
 *
 * The frame: origin at the CENTER of the field, units in INCHES, heading in DEGREES.
 * This is the same frame the Limelight reports in.
 *
 * START and SCORE are examples with made-up numbers. Replace them with real ones, then add
 * the rest of the spots your auto needs.
 */
public final class Poses {
    private Poses() {}

    private static final PoseFactory pose = PoseFactory.degrees();

    //                                         x (in)  y (in)  heading (deg)
    public static final Pose START = pose.of(-60, -36, 0);
    public static final Pose SCORE = pose.of(-36, -12, 45);

    // TODO: add the rest of your spots
}
