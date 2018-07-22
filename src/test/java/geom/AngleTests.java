package geom;

import org.junit.Assert;
import org.junit.Test;
import utils.TestUtils;

public class AngleTests {

    @Test
    public void testFacingNoNormalization() {
        checkFacingNorm(0, 0);
        checkFacingNorm(0.5 * Math.PI, 0.5 * Math.PI);
        checkFacingNorm(-0.5 * Math.PI, -0.5 * Math.PI);
    }

    @Test
    public void testFacingNormalization() {
        // check simple cases
        checkFacingNorm(2 * Math.PI, 0);
        checkFacingNorm(2.5 * Math.PI, 0.5 * Math.PI);
        checkFacingNorm(1.5 * Math.PI, -0.5 * Math.PI);
        checkFacingNorm(-2 * Math.PI, 0);

        // check edge case
        checkFacingNorm(Math.PI, -Math.PI); // FIXME could I get #rounded?
    }

    @Test
    public void testFacingRandomNormalization() {
        for (int i=0; i<1000; i++) {
            Facing randFacing = Facing.of(TestUtils.randomDouble(-1000, 1000));
            Assert.assertNotNull(randFacing);
            Assert.assertTrue(randFacing.radians >= -Math.PI);
            Assert.assertTrue(randFacing.radians < Math.PI);
        }
    }

    private void checkFacingNorm(double givenRadians, double expectedNormRadians) {
        Facing facing = Facing.of(givenRadians);
        Assert.assertEquals(expectedNormRadians, facing.radians, TestUtils.DELTA);
    }

}
