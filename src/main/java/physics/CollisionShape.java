package physics;

import geom.*;

import java.util.Collection;

/**
 * The idea for the methods and techniques to implement this kind of collision detection was taken from
 * http://www.metanetsoftware.com/technique/tutorialA.html
 */
public interface CollisionShape extends Shape {

    /**
     * Generates the facings with respect to another collision shape
     * by which we can use the Separating Axis Theorem to detect and resolve collision.
     * Not all CollisionShape implementations will need the other shape to generate points.
     * Never <code>null</code> (or empty ??? TODO)
     */
    public Collection<Facing> getSatFacings (CollisionShape other);

    /**
     * Generates relevant points of interest with respect to another collision shape,
     * to be used in detecting and resolving collision.
     * Not all CollisionShape implementations will need the other shape to generate points.
     * Never <code>null</code> (or empty ??? TODO)
     */
    public Collection<Point2> getPoints (CollisionShape other);

    /**
     * Gets the projection of this shape onto a line normal to the given facing that passes through the origin.
     * Effectively, this is the "1D shape" of this CollisionShape as it would appear being looked at from the given facing.
     * Never <code>null</code>.
     */
    public Range getProjectionOnFacing (Facing facing);

    /**
     * Gets the point closest to the given LineSegment's start at which it intersects with this shape,
     * or <code>null</code> if it doesn't intersect.
     */
    public Point2 getNearestIntersection (LineSegment2 line);

}
