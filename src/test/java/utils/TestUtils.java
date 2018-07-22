package utils;

public class TestUtils {

    public static final double DELTA = 0.00001;

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


}
