package geom;

import java.util.Objects;

/**
 * Represents an entity or object's facing on a 2D plane.
 * A facing of 0 faces in the positive x direction.
 * Facings are always in the range [-PI, PI)
 */
public class Facing {

    public static final Facing RIGHT = Facing.of(0);
    public static final Facing EAST = Facing.of(0);

    public static final Facing UP = Facing.of(Math.PI / 2);
    public static final Facing NORTH = Facing.of(Math.PI / 2);

    public static final Facing LEFT = Facing.of(-Math.PI);
    public static final Facing WEST = Facing.of(-Math.PI);

    public static final Facing DOWN = Facing.of(-Math.PI / 2);
    public static final Facing SOUTH = Facing.of(-Math.PI / 2);


    public final double radians;

    private Facing(double radians) {
        this.radians = radians;
    }

    /**
     * Normalizes a facing to be in the range [-PI, PI)
     */
    private static double normalizeFacing(double facing) {
        // FIXME does this handle the inclusive/exclusive sides correctly?
        double modFacing = facing % (Math.PI * 2);
        if (modFacing > Math.PI) {
            return modFacing - (2 * Math.PI);
        } else if (modFacing < -Math.PI) {
            return modFacing + (2 * Math.PI);
        } else {
            return modFacing;
        }
    }

    public static Facing of(double radians) {
        return new Facing(radians);
    }

    public Facing rotatedBy(Rotation rot) {
        return Facing.of(radians + rot.radians);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facing facing = (Facing) o;
        return Double.compare(facing.radians, radians) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radians);
    }
}
