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

    public Vector scale(double factor) {
        return new Vector(this.x * factor, this.y * factor);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector subtract(Vector vector) {
        return new Vector(this.x - vector.x, this.y - vector.y);
    }
}
