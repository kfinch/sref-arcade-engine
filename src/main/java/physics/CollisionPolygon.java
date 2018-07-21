package physics;

import geom.*;

import java.util.Arrays;
import java.util.Collection;

public class CollisionPolygon extends Polygon implements CollisionShape {

    private final Collection<Point2> pointsAsCollection;

    public CollisionPolygon(Point2[] points) {
        super(points);
        pointsAsCollection = Arrays.asList(points);
    }


    @Override
    public Collection<Facing> getSatFacings(CollisionShape other) {
        return null; // TODO implement
    }

    @Override
    public Collection<Point2> getPoints(CollisionShape other) {
        return pointsAsCollection;
    }

    @Override
    public Range getProjectionOnFacing(Facing facing) {
        return null; // TODO implement
    }

    @Override
    public Point2 getNearestIntersection(LineSegment2 line) {
        return null;
    }
}
