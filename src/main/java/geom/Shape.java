package geom;

public interface Shape {

    public Shape translatedBy(Vector2 v);

    public Shape rotatedAbout(Point2 center, double angle);

    public AABox getAABox();

}
