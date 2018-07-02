package geom;

/**
 * An immutable representation of a 2D vector.
 */
public class Vector2 {

    public final double x, y;
    public final double magnitude;
    public final double angle; // in radians from origin (+x). Always in the range [-PI, PI]

    private Vector2 (double x, double y, double magnitude, double angle)
    {
        this.x = x;
        this.y = y;
        this.magnitude = magnitude;
        this.angle = normalizeAngle(angle);
    }

    /**
     * Builds a new Vector2 from x and y components
     * @param x
     * @param y
     */
    public Vector2 (double x, double y)
    {
        this(x, y, getMagnitude(x, y), getAngle(x, y));
    }

    /**
     * Builds a new Vector2 that is an exact copy of the given Vector2
     * @param v
     */
    public Vector2 (Vector2 v)
    {
        this(v.x, v.y);
    }

    /**
     * Builds a zero vector
     */
    public Vector2 ()
    {
        this(0, 0);
    }

    /**
     * Builds a new Vector2 from magnitude and angle
     * @param magnitude
     * @param angle
     * @return
     */
    public static Vector2 fromMagnitudeAndAngle (double magnitude, double angle)
    {
        return new Vector2(getX(magnitude, angle), getY(magnitude, angle), magnitude, angle);
    }

    private static double normalizeAngle (double angle)
    {
        double modAngle = angle % (Math.PI * 2);
        if (modAngle > Math.PI) {
            return modAngle - (2 * Math.PI);
        } else if (modAngle < -Math.PI) {
            return modAngle + (2 * Math.PI);
        } else {
            return modAngle;
        }
    }
    private static double getAngle (double x, double y)
    {
        return Math.atan2(y, x);
    }
    private static double getMagnitude (double x, double y)
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    private static double getX (double magnitude, double angle)
    {
        return magnitude * Math.cos(angle);
    }
    private static double getY (double magnitude, double angle)
    {
        return magnitude * Math.sin(angle);
    }

    /**
     * Returns a new Vector2 that is this Vector2 but rotated by the given angle.
     * @param deltaAngle
     * @return
     */
    public Vector2 rotatedBy(double deltaAngle)
    {
        return fromMagnitudeAndAngle(magnitude, angle + deltaAngle);
    }

    /**
     * Returns a new Vector2 that is this Vector2 but rotated to the given angle.
     * @param newAngle
     * @return
     */
    public Vector2 rotatedTo (double newAngle)
    {
        return fromMagnitudeAndAngle(magnitude, newAngle);
    }

    /**
     * Returns a new Vector2 that is this Vector2 but pointed in the opposite direction
     * @return
     */
    public Vector2 reversed ()
    {
        return new Vector2(-x, -y);
    }

    /**
     * Returns a new Vector2 that is this Vector2 but rescaled to the given magnitude.
     * @param newMagnitude
     * @return
     */
    public Vector2 scaledTo (double newMagnitude)
    {
        return fromMagnitudeAndAngle(newMagnitude, angle);
    }

    /**
     * Returns the unit vector of this Vector2.
     * @return
     */
    public Vector2 unitVector()
    {
        return scaledTo(1);
    }

    /**
     * Returns the Vector2 that results from adding this Vector2 and another.
     * @param toAdd
     * @return
     */
    public Vector2 addedTo(Vector2 toAdd)
    {
        return new Vector2(x + toAdd.x, y + toAdd.y);
    }

    /**
     * Returns the Vector2 that results from scaling this Vector2 by the given multiplier.
     * @param mult
     * @return
     */
    public Vector2 multipliedBy(double mult)
    {
        return new Vector2(x*mult, y*mult);
    }

    /**
     * Calculates the dot product of this Vector2 and another.
     * @param toDot
     * @return
     */
    public double dotProduct(Vector2 toDot) { return (x * toDot.x) + (y * toDot.y); }

    /**
     * Gets the Vector2 that is the vector projection of this Vector2 onto the given angle.
     * @param projAngle
     * @return
     */
    public Vector2 vectorProjection(double projAngle)
    {
        double newMag = magnitude * Math.cos(angle - projAngle);
        return fromMagnitudeAndAngle(newMag, projAngle);
    }

    @Override
    public String toString() { return "{" + x + "," + y + "}"; }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector2) {
            Vector2 v = (Vector2) o;
            return (x == v.x) && (y == v.y);
        }
        return false;
    }

}
