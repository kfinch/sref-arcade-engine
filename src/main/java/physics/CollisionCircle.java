package physics;

import com.google.common.collect.Lists;
import geom.*;

import java.util.Collection;

public class CollisionCircle extends Circle implements CollisionShape {

    public CollisionCircle(Point2 center, double radius) {
        super(center, radius);
    }

    public CollisionCircle(double x, double y, double radius) {
        super(x, y, radius);
    }

    @Override
    public Collection<Angle> getSatAngles(CollisionShape other) {
        return null;
    }

    @Override
    public Collection<Point2> getPoints(CollisionShape other) {
        return Lists.newArrayList(center);
    }

    @Override
    public Range getProjectionOnAngle(double angle) {
        double centerProjection = center.projectedOnto(angle);
        return new Range(centerProjection - radius, centerProjection + radius);
    }

    @Override
    public Point2 getNearestIntersection(LineSegment2 line) {
        return null; // TODO implement
    }
}
