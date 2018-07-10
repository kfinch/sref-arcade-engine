package physics;

import com.google.common.collect.Lists;
import geom.AABox;
import geom.Point2;
import geom.Vector2;

import java.util.List;
import java.util.stream.Collectors;

public class Collider {

    private final List<CollisionShape> collisionShapes;

    private AABox aaBox = null;
    private boolean aaBoxHasChanged = true;

    public Collider (List<CollisionShape> collisionShapes) {
        this.collisionShapes = collisionShapes;
    }

    public Collider () {
        this.collisionShapes = Lists.newArrayList();
    }

    public void addCollisionShape(CollisionShape newCollisionShape) {
        this.collisionShapes.add(newCollisionShape);
        aaBoxHasChanged = true;
    }

    public boolean removeCollisionShape(CollisionShape collisionShape) {
        aaBoxHasChanged = true;
        return this.collisionShapes.remove(collisionShape);
    }

    public void translate(Vector2 v) {
        this.collisionShapes.stream().map(s -> s.translatedBy(v)).collect(Collectors.toList());
        aaBoxHasChanged = true;
    }

    public void rotateAbout(Point2 rotCenter, double angle) {
        this.collisionShapes.stream().map(s -> s.rotatedAbout(rotCenter, angle)).collect(Collectors.toList());
        aaBoxHasChanged = true;
    }


    public AABox getBoundingBox() {
        if (aaBoxHasChanged) {
            aaBox = AABox.fromBoxes(collisionShapes.stream().map(cs -> cs.getAABox()).collect(Collectors.toList()));
            aaBoxHasChanged = false;
        }
        return aaBox;
    }


    /**
     * Detects if this collider is colliding with the other, and if so returns a "resolution vector".
     * If there is no collision, this will return null.
     * If there is a collision, the resolution vector returned will be the shortest vector by which this can be translated
     * such that there is no longer a collision.
     */
    public Vector2 resolveCollision(Collider other) {
        // if the collider's bounding boxes don't overlap, they can't possibly collide
        if (!this.getBoundingBox().overlaps(other.getBoundingBox())) {
            return null;
        }

        Vector2 bestResolutionVector = null;
        for (CollisionShape ms : this.collisionShapes) {
            for (CollisionShape os : other.collisionShapes) {
                Vector2 resVector = resolveShapeCollision(ms, os);
                if (resVector != null && (bestResolutionVector == null || resVector.magnitude < bestResolutionVector.magnitude)) {
                    bestResolutionVector = resVector;
                }
            }
        }

        return bestResolutionVector;
    }

    // like, resolveCollision, returns resolution vector for s1 to get out of s2.
    private static Vector2 resolveShapeCollision(CollisionShape s1, CollisionShape s2) {
        // if individual shapes bounding boxes don't overlap, those shapes can't possibly collide
        if (!s1.getAABox().overlaps(s2.getAABox())) {
            return null;
        }

        return null; // TODO implement
    }

}
