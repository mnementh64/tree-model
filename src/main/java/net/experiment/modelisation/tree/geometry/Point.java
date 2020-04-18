package net.experiment.modelisation.tree.geometry;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point translate(Vector vector) {
        return new Point((int) (this.x + vector.x), (int) (this.y + vector.y));
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}
