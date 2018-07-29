package geom;

import org.junit.Assert;
import org.junit.Test;
import utils.TestUtils;

public class VectorTests {

    private static final Vector2 zeroVec = new Vector2(0, 0);
    private static final Vector2 v1 = new Vector2(1, 1);
    private static final Vector2 v2 = new Vector2(2, 2);
    private static final Vector2 vn1 = new Vector2(-1, -1);
    private static final Vector2 horiz = new Vector2(5, 0);
    private static final Vector2 vert = new Vector2(0, 5);

    @Test
    public void testEquality() {
        Vector2 alsoV1 = v1;
        Vector2 alsoOneOne = new Vector2(1, 1);
        Vector2 copiedV1 = new Vector2(v1);

        Assert.assertEquals(v1, alsoV1);
        Assert.assertEquals(v1, alsoOneOne);
        Assert.assertEquals(v1, copiedV1);
        Assert.assertNotEquals(v1, v2);
    }

    @Test
    public void testMagRotFill() {
        Assert.assertEquals(Math.sqrt(2), v1.magnitude, TestUtils.DELTA);
        Assert.assertEquals(Math.PI / 4, v1.angle.radians, TestUtils.DELTA);
        Assert.assertEquals(v1.angle.radians, v2.angle.radians, TestUtils.DELTA);
        Assert.assertEquals(0, horiz.angle.radians, TestUtils.DELTA);
        Assert.assertEquals(Math.PI / 2, vert.angle.radians, TestUtils.DELTA);
        Assert.assertEquals(5, horiz.magnitude, TestUtils.DELTA);
        Assert.assertEquals(5, vert.magnitude, TestUtils.DELTA);
    }

    @Test
    public void testXYFill() {
        Vector2 tenUp = Vector2.fromMagnitudeAndAngle(10, Facing.UP);
        Assert.assertEquals(0, tenUp.x, TestUtils.DELTA);
        Assert.assertEquals(10, tenUp.y, TestUtils.DELTA);

        Vector2 tenLeft = Vector2.fromMagnitudeAndAngle(10, Facing.LEFT);
        Assert.assertEquals(-10, tenLeft.x, TestUtils.DELTA);
        Assert.assertEquals(0, tenLeft.y, TestUtils.DELTA);

        Vector2 negMag = Vector2.fromMagnitudeAndAngle(-10, Facing.LEFT);
        Assert.assertEquals(10, negMag.x, TestUtils.DELTA);
        Assert.assertEquals(0, negMag.y, TestUtils.DELTA);

        Vector2 diag = Vector2.fromMagnitudeAndAngle(10, Math.PI * (3.0/4.0));
        Assert.assertEquals(-Math.sqrt(50), diag.x, TestUtils.DELTA);
        Assert.assertEquals(Math.sqrt(50), diag.y, TestUtils.DELTA);
    }

    @Test
    public void testRotatedBy() {
        Vector2 v1RotQuarter = v1.rotatedBy(Rotation.CCW_QUARTER);
        TestUtils.assertVectorsEqual(new Vector2(-1, 1), v1RotQuarter);
        Assert.assertEquals(-1, v1RotQuarter.x, TestUtils.DELTA);
        Assert.assertEquals(1, v1RotQuarter.y, TestUtils.DELTA);
        Assert.assertEquals(Math.sqrt(2), v1RotQuarter.magnitude, TestUtils.DELTA);
        Assert.assertEquals(Math.PI * 3.0 / 4.0, v1RotQuarter.angle.radians, TestUtils.DELTA);

        Vector2 v1RotHalf = v1.rotatedBy(Rotation.CCW_HALF);
        Vector2 v1RevRotHalf = v1.rotatedBy(Rotation.CW_HALF);
        TestUtils.assertVectorsEqual(vn1, v1RotHalf);
        TestUtils.assertVectorsEqual(vn1, v1RevRotHalf);

        Vector2 v1RotFull = v1.rotatedBy(Rotation.CCW_FULL);
        Vector2 v1RotRevFull = v1.rotatedBy(Rotation.CW_FULL);
        TestUtils.assertVectorsEqual(v1, v1RotFull);
        TestUtils.assertVectorsEqual(v1, v1RotRevFull);

        Vector2 v1NoRot = v1.rotatedBy(Rotation.NONE);
        TestUtils.assertVectorsEqual(v1, v1NoRot);
    }

    @Test
    public void testRotatedTo() {
        Vector2 v1Up = v1.rotatedTo(Facing.UP);
        TestUtils.assertVectorsEqual(new Vector2(0, Math.sqrt(2)), v1Up);

        Vector2 v1Same = v1.rotatedTo(Facing.of(Math.PI / 4));
        TestUtils.assertVectorsEqual(v1, v1Same);
    }

    @Test
    public void testReversed() {
        Vector2 v1Rev = v1.reversed();
        TestUtils.assertVectorsEqual(vn1, v1Rev);
        Vector2 v1RevRev = v1.reversed().reversed();
        TestUtils.assertVectorsEqual(v1, v1RevRev);

        for(int i=0; i<1000; i++) {
            double randX = TestUtils.randomDouble(-100, 100);
            double randY = TestUtils.randomDouble(-100, 100);
            Vector2 randVec = new Vector2(randX, randY);
            Vector2 reversedVec = randVec.reversed();
            Assert.assertEquals(-randX, reversedVec.x, TestUtils.DELTA);
            Assert.assertEquals(-randY, reversedVec.y, TestUtils.DELTA);
        }
    }

    @Test
    public void testScaledTo() {
        TestUtils.assertVectorsEqual(v2, v1.scaledTo(Math.sqrt(8)));
        TestUtils.assertVectorsEqual(new Vector2(1, 0), horiz.scaledTo(1));
        // TODO more?
    }

    @Test
    public void testUnitVector() {
        Vector2 v2Unit = v2.unitVector();
        Assert.assertEquals(Math.sqrt(0.5), v2Unit.x, TestUtils.DELTA);
        Assert.assertEquals(Math.sqrt(0.5), v2Unit.y, TestUtils.DELTA);

        for (int i=0; i<1000; i++) {
            Vector2 randVec = new Vector2(TestUtils.randomDouble(-100, 100), TestUtils.randomDouble(-100, 100));
            Assert.assertEquals(1, randVec.unitVector().magnitude, TestUtils.DELTA);
        }
    }

    @Test
    public void testAddedTo() {
        TestUtils.assertVectorsEqual(new Vector2(3, 3), v1.addedTo(v2));
        TestUtils.assertVectorsEqual(new Vector2(3, 3), v2.addedTo(v1));
        TestUtils.assertVectorsEqual(new Vector2(0, 0), v1.addedTo(vn1));
        TestUtils.assertVectorsEqual(v1, v1.addedTo(new Vector2(0, 0)));
        // TODO more?
    }

    @Test
    public void testMultipliedBy() {
        TestUtils.assertVectorsEqual(v2, v1.multipliedBy(2));
        // TODO more?
    }

    @Test
    public void testDotProduct() {
        Assert.assertEquals(0, horiz.dotProduct(vert), TestUtils.DELTA);
        Assert.assertEquals(0, v1.dotProduct(v1.rotatedBy(Rotation.CCW_QUARTER)), TestUtils.DELTA);
    }

    @Test
    public void testVectorProjection() {
        TestUtils.assertVectorsEqual(new Vector2(1, 0), v1.vectorProjection(horiz));
        TestUtils.assertVectorsEqual(new Vector2(1, 0), v1.vectorProjection(Facing.RIGHT));

        TestUtils.assertVectorsEqual(new Vector2(2, 0), v2.vectorProjection(horiz));
        TestUtils.assertVectorsEqual(new Vector2(0, 2), v2.vectorProjection(vert));

        TestUtils.assertVectorsEqual(new Vector2(-1, 0), vn1.vectorProjection(Facing.RIGHT));
        TestUtils.assertVectorsEqual(new Vector2(-1, 0), vn1.vectorProjection(Facing.LEFT));
    }
}
