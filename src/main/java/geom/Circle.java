package geom;

public class Circle implements Shape {

    public final Point2 center;
    public final double radius;

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
    public AABox getAABox() {
        return AABox.fromCenterAndBounds(center, radius, radius);
    }
}
