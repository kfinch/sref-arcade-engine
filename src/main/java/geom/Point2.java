package geom;

import java.util.Objects;

/**
 * An immutable representation of a point on a 2D plane.
 */
public class Point2 implements Shape
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
    @Override
    public Point2 translatedBy(Vector2 translate)
    {
        return new Point2(x + translate.x, y + translate.y);
    }

    @Override
    public Point2 rotatedAbout(Point2 rotCenter, double angle) {
        return this.translatedBy(rotCenter.vectorTo(this).rotatedBy(angle));
    }

    @Override
    public AABox getAABox()
    {
        return new AABox(x, x, y, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2 point2 = (Point2) o;
        return Double.compare(point2.x, x) == 0 &&
                Double.compare(point2.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
