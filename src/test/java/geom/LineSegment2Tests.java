package geom;

import org.junit.Assert;
import org.junit.Test;

public class LineSegment2Tests {

    @Test
    public void testNoIntersection () {
        // parallel
        assertNoIntersect(new LineSegment2(0, 0, 5, 0), new LineSegment2(0, 1, 5, 1));
        assertNoIntersect(new LineSegment2(0, 0, 0, 5), new LineSegment2(1, 0, 1, 5));
        assertNoIntersect(new LineSegment2(0, 0, 2, 2), new LineSegment2(1, 0, 3, 2));

        // non parallel not long enough
        assertNoIntersect(new LineSegment2(0, 0, 0, 5), new LineSegment2(10, 0, 5, 5)); // first vert
        assertNoIntersect(new LineSegment2(10, 0, 5, 5), new LineSegment2(0, 0, 0, 5)); // second vert
        assertNoIntersect(new LineSegment2(0, 0, 2, 2), new LineSegment2(10, 0, 8, 2)); // neither vert
    }

    @Test
    public void testSimpleIntersection () {
        assertIntersectAt(new LineSegment2(2, 0, 2, 4), new LineSegment2(4, 0, 0, 4), new Point2(2, 2)); // first vert
        assertIntersectAt(new LineSegment2(4, 0, 0, 4), new LineSegment2(2, 0, 2, 4), new Point2(2, 2)); // second vert
        assertIntersectAt(new LineSegment2(0, 0, 4, 4), new LineSegment2(4, 0, 0, 4), new Point2(2, 2)); // neither vert
    }

    @Test
    public void testMultipleIntersection () {
        // for intersecting parallel lines, should choose intersection point closest to the start of segment 1
        assertIntersectAt(new LineSegment2(0, 0, 0, 4), new LineSegment2(0, 1, 0, 6), new Point2(0, 1)); // vert
        assertIntersectAt(new LineSegment2(0, 4, 0, 0), new LineSegment2(0, 1, 0, 6), new Point2(0, 4)); // vert
        assertIntersectAt(new LineSegment2(0, 0, 4, 4), new LineSegment2(1, 1, 6, 6), new Point2(1, 1)); // not vert
        assertIntersectAt(new LineSegment2(4, 4, 0, 0), new LineSegment2(1, 1, 6, 6), new Point2(4, 4)); // not vert
    }

    @Test
    public void testEdgeIntersection () {
        assertIntersectAt(new LineSegment2(0, 0, 0, 4), new LineSegment2(0, 4, 6, 6), new Point2(0, 4));
        assertIntersectAt(new LineSegment2(0, 0, 0, 4), new LineSegment2(6, 4, -6, 4), new Point2(0, 4));
    }

    private void assertNoIntersect (LineSegment2 seg1, LineSegment2 seg2) {
        Assert.assertNull(LineSegment2.intersection(seg1, seg2));
    }

    private void assertIntersectAt (LineSegment2 seg1, LineSegment2 seg2, Point2 intersection) {
        Assert.assertEquals(intersection, LineSegment2.intersection(seg1, seg2));
    }

}
