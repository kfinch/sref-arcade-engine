package geom;

import java.util.Arrays;

public class Polygon implements Shape {

    public final Point2[] points;

    private AABox aaBox = null;

    public Polygon(Point2[] points)
    {
        this.points = points;
    }

    @Override
    public Polygon translatedBy(Vector2 v)
    {
        Point2[] newPoints = Arrays.stream(points).map(p -> p.translatedBy(v)).toArray(Point2[]::new);
        return new Polygon(newPoints);
    }

    @Override
    public Polygon rotatedAbout(Point2 rotCenter, double angle)
    {
        Point2[] newPoints = Arrays.stream(points).map(p -> p.rotatedAbout(rotCenter, angle)).toArray(Point2[]::new);
        return new Polygon(newPoints);
    }

    @Override
    public AABox getAABox()
    {
        if (aaBox == null) {
            aaBox = generateAABox();
        }
        return aaBox;
    }
    private AABox generateAABox()
    {
        double xMin = Double.POSITIVE_INFINITY;
        double xMax = Double.NEGATIVE_INFINITY;
        double yMin = Double.POSITIVE_INFINITY;
        double yMax = Double.NEGATIVE_INFINITY;

        for (Point2 point : points) {
            xMin = point.x < xMin ? point.x : xMin;
            xMax = point.x > xMax ? point.x : xMax;
            yMin = point.y < yMin ? point.y : yMin;
            yMax = point.y < yMax ? point.y : yMax;
        }

        return new AABox(xMin, xMax, yMin, yMax);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon polygon = (Polygon) o;
        return Arrays.equals(points, polygon.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }
}
