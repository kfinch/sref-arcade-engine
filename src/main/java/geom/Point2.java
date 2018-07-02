package geom;

/**
 * An immutable representation of a point on a 2D plane.
 */
public class Point2
{

    public final double x, y;

    public Point2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Point2(Point2 p)
    {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Returns the distance to another given Point2
     */
    public double distanceTo(Point2 other)
    {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * Returns the Vector2 that goes from this Point2 to another.
     */
    public Vector2 vectorTo(Point2 other)
    {
        return new Vector2(other.x - x, other.y - y);
    }

    /**
     * Returns the Point2 that is this Point2 translated by the given Vector2.
     * @param translate
     * @return
     */
    public Point2 translatedBy(Vector2 translate)
    {
        return new Point2(x + translate.x, y + translate.y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Point2) {
            Point2 p = (Point2) o;
            return (x == p.x) && (y == p.y);
        }
        return false;
    }

}
