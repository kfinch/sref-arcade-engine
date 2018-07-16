package geom;

/**
 * In geom, angles are always expressed as doubles representing radians from origin,
 * where the origin travels along the positive x axis, and positive rotation is counter-clockwise.
 * We expect that all absolute angles will be in the range [-PI, PI]
 *
 * This class provides static helpers for dealing with angle related calculations.
 */
public class Angle
{
    public static final Angle RIGHT = new Angle(0);
    public static final Angle EAST = new Angle(0);

    public static final Angle UP = new Angle(Math.PI / 2);
    public static final Angle NORTH = new Angle(Math.PI / 2);

    public static final Angle LEFT = new Angle(Math.PI);
    public static final Angle WEST = new Angle(Math.PI);

    public static final Angle DOWN = new Angle(-Math.PI / 2);
    public static final Angle SOUTH = new Angle(-Math.PI / 2);


    public final double radians;

    public Angle(double radians) {
        this.radians = normalize(radians);
    }
    private static double normalize (double angle) {
        double modAngle = angle % (Math.PI * 2);
        if (modAngle > Math.PI) {
            return modAngle - (2 * Math.PI);
        } else if (modAngle < -Math.PI) {
            return modAngle + (2 * Math.PI);
        } else {
            return modAngle;
        }
    }

    public Angle addedTo (Angle other) {
        return new Angle(this.radians + other.radians);
    }
    public Angle addedTo (double rads) {
        return new Angle(this.radians + rads);
    }

    /**
     * Returns an Angle representing the opposite of this Angle.
     * Assumes this Angle is being treated as an absolute angle.
     * @return
     */
    public Angle reversed () {
        return new Angle(this.radians + Math.PI);
    }

    /**
     * Returns an Angle representing the opposite of this Angle.
     * Assumes this Angle is being treated as relative, i.e. a rotation.
     * @return
     */
    public Angle negative() {
        return new Angle(-this.radians);
    }

}
