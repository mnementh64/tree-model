package net.experiment.modelisation.tree.geometry;

public class Segment {
    public Point a;
    public Point b;

    public Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "a=(" + a +
                ") --> b=(" + b +
                ")}";
    }
}
