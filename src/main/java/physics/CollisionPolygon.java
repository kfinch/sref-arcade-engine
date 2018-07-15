package physics;

import geom.LineSegment2;
import geom.Point2;
import geom.Polygon;
import geom.Range;

import java.util.Arrays;
import java.util.Collection;

public class CollisionPolygon extends Polygon implements CollisionShape {

    private final Collection<Point2> pointsAsCollection;

    public CollisionPolygon(Point2[] points) {
        super(points);
        pointsAsCollection = Arrays.asList(points);
    }


    @Override
    public Collection<Double> getSatAngles(CollisionShape other) {
        return null;
    }

    @Override
    public Collection<Point2> getPoints(CollisionShape other) {
        return pointsAsCollection;
    }

    @Override
    public Range getProjectionOnAngle(double angle) {
        return null;
    }

    @Override
    public Point2 getNearestIntersection(LineSegment2 line) {
        return null;
    }
}
