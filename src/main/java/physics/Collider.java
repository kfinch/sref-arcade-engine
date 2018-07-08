package physics;

import com.google.common.collect.Lists;
import geom.Point2;
import geom.Shape;
import geom.Vector2;

import java.util.List;
import java.util.stream.Collectors;

public class Collider {

    private final List<Shape> collisionShapes;

    public Collider (List<Shape> collisionShapes) {
        this.collisionShapes = collisionShapes;
    }

    public Collider () {
        this.collisionShapes = Lists.newArrayList();
    }

    public void addCollisionShape(Shape newCollisionShape) {
        this.collisionShapes.add(newCollisionShape);
    }

    public boolean removeCollisionShape(Shape collisionShape) {
        return this.collisionShapes.remove(collisionShape);
    }

    public void translate(Vector2 v) {
        this.collisionShapes.stream().map(s -> s.translatedBy(v)).collect(Collectors.toList());
    }

    public void rotateAbout(Point2 rotCenter, double angle) {
        this.collisionShapes.stream().map(s -> s.rotatedAbout(rotCenter, angle)).collect(Collectors.toList());
    }


    // TODO implement shape to shape collision

}
