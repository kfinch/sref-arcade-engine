package geom;

/**
 * In geom, angles are always expressed as doubles representing radians from origin,
 * where the origin travels along the positive x axis, and positive rotation is counter-clockwise.
 * We expect that all absolute angles will be in the range [-PI, PI]
 *
 * This class provides static helpers for dealing with angle related calculations.
 */
public class Angles
{
    public static final double RIGHT = 0;
    public static final double EAST = 0;

    public static final double UP = Math.PI / 2;
    public static final double NORTH = Math.PI / 2;

    public static final double LEFT = Math.PI;
    public static final double WEST = Math.PI;

    public static final double DOWN = -Math.PI / 2;
    public static final double SOUTH = -Math.PI / 2;

    public static double normalized (double angle) {
        double modAngle = angle % (Math.PI * 2);
        if (modAngle > Math.PI) {
            return modAngle - (2 * Math.PI);
        } else if (modAngle < -Math.PI) {
            return modAngle + (2 * Math.PI);
        } else {
            return modAngle;
        }
    }

    public static double add (double a1, double a2) {
        return normalized(a1 + a2);
    }

    public static double reversed (double angle) {
        return add(angle, Math.PI);
    }

}
