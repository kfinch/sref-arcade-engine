package geom;

/**
 * An axis-aligned box, useful for calculating collision
 */
public class AABox implements Shape {

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

    @Override
    public AABox translatedBy(Vector2 v)
    {
        return new AABox(xMin + v.x, xMax + v.x, yMin + v.y, yMax + v.y);
    }

    @Override
    public AABox getAABox()
    {
        return this;
    }

}
