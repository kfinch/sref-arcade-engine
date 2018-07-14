package physics;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import geom.AABox;
import geom.Point2;
import geom.Range;
import geom.Vector2;

import java.util.List;
import java.util.Set;
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

    public int getNumberCollisionShapes() {
        return collisionShapes.size();
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
        // if this collider and the other only have one shape, can just do a simple one shape collision
        if (this.collisionShapes.size() == 1 && other.collisionShapes.size() == 1) {
            return resolveShapeCollision(this.collisionShapes.get(0), other.collisionShapes.get(0));
        }

        // if the collider's bounding boxes don't overlap, they can't possibly collide
        if (!this.getBoundingBox().overlaps(other.getBoundingBox())) {
            return null;
        }

        Vector2 bestResolutionVector = null;
        for (CollisionShape ms : this.collisionShapes) {
            for (CollisionShape os : other.collisionShapes) {
                Vector2 resVector = resolveShapeCollision(ms, os);
                // TODO handle multiple collisions
            }
        }

        return bestResolutionVector;
    }

    // like resolveCollision, returns resolution vector for s1 to get out of s2.
    private static Vector2 resolveShapeCollision(CollisionShape s1, CollisionShape s2) {
        // if individual shapes bounding boxes don't overlap, those shapes can't possibly collide
        if (!s1.getAABox().overlaps(s2.getAABox())) {
            return null;
        }

        Vector2 resolutionVector = null; // looking for shortest resolution vector
        Set<Double> anglesToCheck = Sets.newHashSet(); // a set to avoid checking same angle more than once
        anglesToCheck.addAll(s1.getSatAngles(s2));
        anglesToCheck.addAll(s2.getSatAngles(s1));

        for (double projAngle : anglesToCheck) {
            Range s1r = s1.getProjectionOnAngle(projAngle);
            Range s2r = s2.getProjectionOnAngle(projAngle);
            if (s1r.overlap(s2r) == null) { // FIXME use overlapExclusive?
                return null;
            }

            double minDisplace = getMinDisplace(s1r, s2r);
            if (resolutionVector != null && minDisplace > resolutionVector.magnitude) {
                continue; // current res vector is smaller
            }
            resolutionVector = Vector2.fromMagnitudeAndAngle(minDisplace, projAngle); // TODO check angle
        }

        return resolutionVector;
    }

    // returns shortest distance by which r1 could be displaced to remove it from r2
    private static double getMinDisplace(Range r1, Range r2) {
        double negDisplace = r1.max - r2.min;
        double posDisplace = r2.max - r1.min;
        if (negDisplace > posDisplace) {
            return posDisplace;
        } else {
            return -negDisplace;
        }
    }

}
