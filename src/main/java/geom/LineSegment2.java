package geom;

import java.util.Objects;

public class LineSegment2 {

    public final Point2 start, end;

    private AABox boundingBox = null;

    public LineSegment2 (Point2 start, Point2 end)
    {
        this.start = start;
        this.end = end;
    }

    public LineSegment2 (double x1, double y1, double x2, double y2) {
        this(new Point2(x1, y1), new Point2(x2, y2));
    }

    private AABox getBoundingBox() {
        if (boundingBox == null) {
            boundingBox = new AABox(Math.min(start.x, end.x), Math.max(start.x, end.x),
                    Math.min(start.y, end.y), Math.max(start.y, end.y));
        }
        return boundingBox;
    }

    /**
     * Returns the point of intersection between this segment and another,
     * or <code>null</code> if they don't intersect.
     * If the segments have multiple points of intersection, such as segments partially overlaid atop one another,
     * then the returned Point is the point closest to l1.
     */
    public static Point2 intersection (LineSegment2 l1, LineSegment2 l2) {
        if (!l1.getBoundingBox().overlaps(l2.getBoundingBox())) {
            return null; // if bounding boxes don't collide, can't possibly intersect
        }

        boolean isL1vert = l1.start.x == l1.end.x;
        boolean isL2vert = l2.start.x == l2.end.x;
        if (isL1vert && isL2vert) { // both lines are vertical
            return bothVertIntersectionHelper(l1, l2);
        } else if (isL1vert) { // only line 1 is vertical
            return oneVertIntersectionHelper(l1, l2);
        } else if (isL2vert) { // only line 2 is vertical
            return oneVertIntersectionHelper(l2, l1);
        } else { // neither are vertical
            return nonVertIntersectionHelper(l1, l2);
        }
    }

    // gets intersection between two segments where both are vertical
    private static Point2 bothVertIntersectionHelper (LineSegment2 l1, LineSegment2 l2) {
        if (l1.start.x != l2.start.x) { // two vertical segments on different x can't possibly intersect
            return null;
        }
        double ry = rangeIntersectHelper(l1.start.y, l1.end.y, l2.start.y, l2.end.y);
        double rx = l1.start.x;
        return new Point2(rx, ry);
    }

    // gets intersection between two segments, where v is vertical and nv is not vertical
    private static Point2 oneVertIntersectionHelper (LineSegment2 v, LineSegment2 nv) {
        double rx = v.start.x; // any solution must be on the vertical's x
        // convert sloped line to "y = a*x + b" form
        double nva = (nv.end.y - nv.start.y) / (nv.end.x - nv.start.x);
        double nvb = nv.start.y - (nva * nv.start.x);
        double ry = nva * rx + nvb; // use to solve for y ...
        // determine if rx and ry are actually within the bounds of both lines
        if (new Range(v.start.y, v.end.y).contains(ry) && new Range(nv.start.y, nv.end.y).contains(ry)) {
            return new Point2(rx, ry);
        } else {
            return null;
        }
    }

    // gets intersection between two segments, neither of which are vertical
    private static Point2 nonVertIntersectionHelper (LineSegment2 l1, LineSegment2 l2) {
        // if neither are vertical, we can convert both to the form "y = a*x + b"
        double a1 = (l1.end.y - l1.start.y) / (l1.end.x - l1.start.x);
        double b1 = l1.start.y - (a1 * l1.start.x);
        double a2 = (l2.end.y - l2.start.y) / (l2.end.x - l2.start.x);
        double b2 = l2.start.y - (a2 * l2.start.x);

        if (a1 == a2) { // parallel
            if (b1 != b2) { // can't possibly intersect if they have different intersects
                return null;
            }
            double rx = rangeIntersectHelper(l1.start.x, l1.end.x, l2.start.x, l2.end.x); // get 'best' x intersection
            if (!Double.isNaN(rx)) {
                double ry = a1*rx + b1; // solve for y
                return new Point2(rx, ry);
            } else {
                return null;
            }
        } else { // not parallel
            // rearrange the two "y = a*x + b" equations to solve for x and y
            double rx = (b2 - b1) / (a1 - a2);
            double ry = a1 * rx + b1;
            // check that both segments extend far enough to reach their intersection
            if (new Range(l1.start.x, l1.end.x).contains(rx) && new Range(l2.start.x, l2.end.x).contains(rx)) {
                return new Point2(rx, ry);
            } else {
                return null;
            }
        }
    }

    // given the range from l1s to l1e and the range from l2s to l2e,
    // gets the overlapping number closest to l1s, or NaN if they don't overlap
    private static double rangeIntersectHelper (double l1s, double l1e, double l2s, double l2e) {
        Range overlap = new Range(l1s, l1e).overlap(new Range(l2s, l2e));
        if (overlap == null) {
            return Double.NaN;
        } else if (overlap.contains(l1s)) {
            return l1s;
        } else {
            double diffToMin = Math.abs(l1s - overlap.min);
            double diffToMax = Math.abs(l1s - overlap.max);
            return (diffToMin < diffToMax) ? overlap.min : overlap.max;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSegment2 that = (LineSegment2) o;
        return Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "Seg{ " + start + " -> " + end + " }";
    }
}
