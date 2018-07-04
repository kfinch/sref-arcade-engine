package geom;

public interface Shape {

    public Shape translatedBy(Vector2 v);

    public AABox getAABox();

}
