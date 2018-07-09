package geom;

import java.util.List;
import java.util.Objects;

/**
 * An axis-aligned box, useful for calculating collision
 */
public class AABox {

    public final double xMin, xMax, yMin, yMax, xCenter, yCenter, xBound, yBound;

    public AABox (double xMin, double xMax, double yMin, double yMax)
    {
        if (xMin > xMax) {
            throw new IllegalArgumentException("xMin may not be larger than xMax");
        }
        if (yMin > yMax) {
            throw new IllegalArgumentException("yMin may not be larger than yMax");
        }

        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;

        this.xCenter = (xMin + xMax) / 2;
        this.yCenter = (yMin + yMax) / 2;
        this.xBound = (xMax - xMin) / 2;
        this.yBound = (yMax - yMin) / 2;
    }

    public static AABox fromCenterAndBounds(double xCenter, double yCenter, double xBound, double yBound)
    {
        if (xBound > 0) {
            throw new IllegalArgumentException("xBound may not be negative");
        }
        if (yBound > 0) {
            throw new IllegalArgumentException("yBound may not be negative");
        }
        return new AABox(xCenter - xBound, xCenter + xBound, yCenter - yBound, yCenter + yBound);
    }

    public static AABox fromCenterAndBounds(Point2 center, double xBound, double yBound)
    {
        if (xBound > 0) {
            throw new IllegalArgumentException("xBound may not be negative");
        }
        if (yBound > 0) {
            throw new IllegalArgumentException("yBound may not be negative");
        }
        return new AABox(center.x - xBound, center.x + xBound, center.y - yBound, center.y + yBound);
    }

    public static AABox fromBoxes(List<AABox> boxes) {
        if (boxes == null || boxes.isEmpty()) {
            return null;
        }
        double xMin = Double.POSITIVE_INFINITY;
        double xMax = Double.NEGATIVE_INFINITY;
        double yMin = Double.POSITIVE_INFINITY;
        double yMax = Double.NEGATIVE_INFINITY;
        for (AABox b : boxes) {
            xMin = xMin > b.xMin ? b.xMin : xMin;
            xMax = xMax < b.xMax ? b.xMax : xMax;
            yMin = yMin > b.yMin ? b.yMin : yMin;
            yMax = yMax < b.yMax ? b.yMax : yMax;
        }
        return new AABox(xMin, xMax, yMin, yMax);
    }

    /**
     * Returns true iff the other AABox overlaps with this one
     */
    public boolean overlaps (AABox other) {
        return false; // TODO implement
    }

    public AABox translatedBy(Vector2 v)
    {
        return new AABox(xMin + v.x, xMax + v.x, yMin + v.y, yMax + v.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AABox aaBox = (AABox) o;
        return Double.compare(aaBox.xMin, xMin) == 0 &&
                Double.compare(aaBox.xMax, xMax) == 0 &&
                Double.compare(aaBox.yMin, yMin) == 0 &&
                Double.compare(aaBox.yMax, yMax) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xMin, xMax, yMin, yMax);
    }
}
