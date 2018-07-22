package geom;

import org.junit.Assert;
import org.junit.Test;
import utils.TestUtils;

public class PointTests {

    private static final Point2 origin = new Point2(0, 0);
    private static final Point2 p1 = new Point2(1, 1);
    private static final Point2 p1High = new Point2(1, 5);
    private static final Point2 p2 = new Point2(2, 2);
    private static final Point2 pn1 = new Point2(-1, -1);

    @Test
    public void testEquality() {
        Point2 alsoP1 = p1;
        Point2 alsoOneOne = new Point2(1, 1);
        Point2 copiedP1 = new Point2(p1);

        Assert.assertEquals(p1, alsoP1);
        Assert.assertEquals(p1, alsoOneOne);
        Assert.assertEquals(p1, copiedP1);
        Assert.assertNotEquals(p1, p2);
    }

    @Test
    public void testDistanceTo() {
        Assert.assertEquals(0, p1.distanceTo(p1), TestUtils.DELTA);
        Assert.assertEquals(4, p1.distanceTo(p1High), TestUtils.DELTA);
        Assert.assertEquals(Math.sqrt(2), p1.distanceTo(p2), TestUtils.DELTA);
        Assert.assertEquals(Math.sqrt(2), p2.distanceTo(p1), TestUtils.DELTA);
        Assert.assertEquals(Math.sqrt(8), p1.distanceTo(pn1), TestUtils.DELTA);
    }

    @Test
    public void testVectorTo() {
        Assert.assertEquals(new Vector2(1,1), p1.vectorTo(p2));
        Assert.assertEquals(new Vector2(-1,-1), p2.vectorTo(p1));
        Assert.assertEquals(new Vector2(0,0), p1.vectorTo(p1));
    }

    @Test
    public void testTranslatedBy() {
        Point2 p1Up10 = p1.translatedBy(new Vector2(0, 10));
        Assert.assertEquals(new Point2(1, 11), p1Up10);

        Vector2 v1To2 = p1.vectorTo(p2);
        Assert.assertEquals(p2, p1.translatedBy(v1To2));
    }

    @Test
    public void testRotatedAbout() {
        Point2 p1QuarterTurn = p1.rotatedAbout(origin, Rotation.CCW_QUARTER);
        Assert.assertEquals(new Point2(-1, 1), p1QuarterTurn);

        Point2 p1HalfTurn = p1.rotatedAbout(origin, Rotation.CCW_HALF);
        Assert.assertEquals(new Point2(-1, -1), p1HalfTurn);
        Point2 p1RevHalfTurn = p1.rotatedAbout(origin, Rotation.CCW_HALF);
        Assert.assertEquals(new Point2(-1, -1), p1RevHalfTurn);

        Point2 p1FullTurn = p1.rotatedAbout(origin, Rotation.CCW_FULL);
        Assert.assertEquals(p1, p1FullTurn);

        Point2 p1QuarterAboutP2 = p1.rotatedAbout(p2, Rotation.CCW_QUARTER);
        Assert.assertEquals(new Point2(3, 1), p1QuarterAboutP2);

        Point2 p1BigSpin = p1.rotatedAbout(origin, Rotation.of(Math.PI * 5)); // 2 and a half turns
        Assert.assertEquals(new Point2(-1, -1), p1BigSpin);

        Point2 p1NoRot = p1.rotatedAbout(origin, Rotation.NONE);
        Assert.assertEquals(p1, p1NoRot);
    }

}
