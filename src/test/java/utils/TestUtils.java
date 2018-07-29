package utils;

import geom.Facing;
import geom.Point2;
import geom.Rotation;
import geom.Vector2;
import org.junit.Assert;

import java.security.cert.PolicyNode;

public class TestUtils {

    public static final double DELTA = 0.00000001;

    public static double randomDouble(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException();
        }
        double range = max - min;
        return min + Math.random()*range;
    }

    public static int randomInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException();
        }
        double range = max - min + 1; // truncates down
        return min + (int) (Math.random()*range);
    }

    public static boolean doubleCompare(double a, double b, double maxError) {
        return Math.abs(a - b) < maxError;
    }
    public static boolean doubleCompare(double a, double b) {
        return doubleCompare(a, b, DELTA);
    }
    public static boolean facingCompare(Facing a, Facing b) {
        return doubleCompare(a.radians, b.radians);
    }
    public static boolean rotationCompare(Rotation a, Rotation b) {
        return doubleCompare(a.radians, b.radians);
    }
    public static boolean pointCompare(Point2 a, Point2 b) {
        return doubleCompare(a.x, b.x) && doubleCompare(a.y, b.y);
    }
    public static boolean vectorCompare(Vector2 a, Vector2 b) {
        return doubleCompare(a.x, b.x) && doubleCompare(a.y, b.y);
    }

    public static void assertPointsEqual(Point2 expected, Point2 actual) {
        Assert.assertTrue("\nExpected: " + expected + "\nActual: " + actual, pointCompare(expected, actual));
    }
    public static void assertVectorsEqual(Vector2 expected, Vector2 actual) {
        Assert.assertTrue("\nExpected: " + expected + "\nActual: " + actual, vectorCompare(expected, actual));
    }


}
