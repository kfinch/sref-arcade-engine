package geom;

import java.util.Objects;

/**
 * Represents a relative angle or a change in angle.
 * A positive value is a counter-clockwise rotation, a negative value is a clockwise rotation.
 */
public class Rotation {

    public static final Rotation NONE = Rotation.of(0);

    public static final Rotation CW_QUARTER = Rotation.of(-Math.PI / 2);
    public static final Rotation CW_HALF = Rotation.of(-Math.PI);
    public static final Rotation CW_FULL = Rotation.of(-2*Math.PI);

    public static final Rotation CCW_QUARTER = Rotation.of(Math.PI / 2);
    public static final Rotation CCW_HALF = Rotation.of(Math.PI);
    public static final Rotation CCW_FULL = Rotation.of(2*Math.PI);

    public final double radians;

    private Rotation(double radians) {
        this.radians = radians;
    }

    public static Rotation of(double radians) {
        return new Rotation(radians);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rotation rotation = (Rotation) o;
        return Double.compare(rotation.radians, radians) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radians);
    }
}
