package geom;

import java.util.Objects;

public class Range {

    public final double min, max;

    /**
     * Builds a range from its two limits, which may be in either order.
     * Range does not specify if the limits are inclusive or exclusive.
     */
    public Range (double l1, double l2) {
        if (Double.isNaN(l1) || Double.isNaN(l2)) {
            throw new IllegalArgumentException("neither limit may be NaN");
        }
        if (l1 > l2) {
            this.min = l2;
            this.max = l1;
        } else {
            this.min = l1;
            this.max = l2;
        }
    }

    public double getSize() { return max - min; }

    public boolean contains(double v) {
        return min <= v && v <= max;
    }

    public boolean containsExclusive(double v) {
        return min < v && v < max;
    }

    /**
     * Returns a range representing the overlap of this range and another.
     * Null if there is no overlap, and returns a 0 size Range when the two Ranges are only 'touching'.
     */
    public Range overlap (Range other) {
        double biggestMin = Math.max(min, other.min);
        double smallestMax = Math.min(max, other.max);
        if (biggestMin > smallestMax) {
            return null;
        } else {
            return new Range(biggestMin, smallestMax);
        }
    }

    /**
     * Returns a range representing the overlap of this range and another.
     * Null if there is no overlap, and if the Ranges are only 'touching' that counts as no overlap.
     */
    public Range overlapExclusive (Range other) {
        Range overlap = overlap(other);
        if (overlap.getSize() == 0) {
            return null;
        }
        return overlap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return Double.compare(range.min, min) == 0 &&
                Double.compare(range.max, max) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}
