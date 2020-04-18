package net.experiment.modelisation.tree.geometry;

public class Vector {
    public double x;
    public double y;

    public Vector() {
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector scale(double percentage) {
        return new Vector(this.x * percentage, this.y * percentage);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
