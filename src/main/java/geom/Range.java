package geom;

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

    /**
     * Returns a range representing the overlap of this range and another.
     * Null if there is no overlap, and returns a 0 size Range when the two Ranges are only 'touching'.
     */
    public Range overlap (Range other) {
        return null; // TODO implement
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



}
