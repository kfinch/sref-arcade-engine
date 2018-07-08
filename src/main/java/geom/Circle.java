package geom;

import java.util.Objects;

public class Circle implements Shape {

    public final Point2 center;
    public final double radius;

    private AABox aaBox;

    public Circle (Point2 center, double radius)
    {
        this.center = center;
        this.radius = radius;
    }

    public Circle (double x, double y, double radius)
    {
        this.center = new Point2(x, y);
        this.radius = radius;
    }

    @Override
    public Circle translatedBy(Vector2 v) {
        return new Circle(center.translatedBy(v), radius);
    }

    @Override
    public Circle rotatedAbout(Point2 rotCenter, double angle) {
        return new Circle(center.rotatedAbout(rotCenter, angle), radius);
    }

    @Override
    public AABox getAABox() {
        if (aaBox == null) {
            aaBox = generateAABox();
        }
        return aaBox;
    }
    private AABox generateAABox() {
        return AABox.fromCenterAndBounds(center, radius, radius);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 &&
                Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }
}
