package geom;

import java.util.Objects;

public class LineSegment2 {

    public final Point2 start, finish;

    public LineSegment2 (Point2 start, Point2 finish)
    {
        this.start = start;
        this.finish = finish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSegment2 that = (LineSegment2) o;
        return Objects.equals(start, that.start) &&
                Objects.equals(finish, that.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, finish);
    }
}
