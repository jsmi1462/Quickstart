package org.firstinspires.ftc.teamcode.team;

import static com.pedropathing.api.Paths.line;

import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

/**
 * Each leg of the autonomous is one line here, built from named Poses.
 * A leg drives in a straight line and turns smoothly from the start heading to the end heading.
 * Curves are available too: see Paths.curve(...) and Paths.through(...) in the Pedro library.
 */
public final class Routes {
    private Routes() {}

    public static Path startToScore()  { return leg(Poses.START,  Poses.SCORE);  }
    public static Path scoreToPickup() { return leg(Poses.SCORE,  Poses.PICKUP); }
    public static Path pickupToScore() { return leg(Poses.PICKUP, Poses.SCORE);  }
    public static Path scoreToPark()   { return leg(Poses.SCORE,  Poses.PARK);   }

    private static Path leg(Pose from, Pose to) {
        return line(from, to).linear(from, to);
    }
}
