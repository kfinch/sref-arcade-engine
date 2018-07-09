package physics;

import geom.Point2;
import geom.Shape;

import java.util.Collection;

public interface CollisionShape extends Shape {

    public Collection<Double> getSatLines (CollisionShape other);

    public Collection<Point2> getPoints (CollisionShape other);

    // TODO getAngleProjection

    // TODO getFrame

}
