package physics;

import geom.*;

import java.util.Arrays;
import java.util.Collection;

public class CollisionPolygon extends Polygon implements CollisionShape {

    // TODO should I be manually memoizing?
    private Collection<Angle> satAngles;
    private Collection<Point2> pointsAsCollection;
    private Collection<LineSegment2> segments;

    public CollisionPolygon(Point2[] points) {
        super(points);
    }


    @Override
    public Collection<Angle> getSatAngles(CollisionShape other) {
        if (satAngles == null) {
            for (int i=1; i<points.length; i++) {
                Vector2 segmentVec = points[i-1].vectorTo(points[i]);
                satAngles.add(segmentVec.angle.addedTo(Math.PI / 2));
            }
            Vector2 segmentVec = points[points.length-1].vectorTo(points[0]);
            satAngles.add(segmentVec.angle.addedTo(Math.PI / 2));
        }
        return satAngles;
    }

    @Override
    public Collection<Point2> getPoints(CollisionShape other) {
        if (pointsAsCollection == null) {
            pointsAsCollection = Arrays.asList(points);
        }
        return pointsAsCollection;
    }

    @Override
    public Range getProjectionOnAngle(double angle) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (Point2 p : points) {
            double loc = p.projectedOnto(angle);
            min = loc < min ? loc : min;
            max = loc > max ? loc : max;
        }
        return new Range(min, max);
    }

    @Override
    public Point2 getNearestIntersection(LineSegment2 line) {
        return null;
    }
}
