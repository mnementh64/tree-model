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

    public Vector normalized() {
        double module = this.module();
        return new Vector(x / module, y / module);
    }

    public Vector addModule(double amount) {
        double oldModule = this.module();
        double newModule = oldModule + amount;
        Vector vector = new Vector(this.x * newModule / oldModule, this.y * newModule / oldModule);
//        System.out.println("Module : " + oldModule + " add " + amount + " --> " + vector.module());
        return vector;
    }

    public double module() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    // see https://fr.wikipedia.org/wiki/Rotation_vectorielle#%C3%89criture_matricielle
    public Vector rotate(double deltaAngleInDegre) {
        double angleInRadians = Math.toRadians(deltaAngleInDegre);
        double newX = this.x * Math.cos(angleInRadians) - this.y * Math.sin(angleInRadians);
        double newY = this.x * Math.sin(angleInRadians) + this.y * Math.cos(angleInRadians);
        return new Vector(newX, newY);
    }

    public double computeAngleInDegres() {
        return x != 0 ? Math.toDegrees(Math.atan(y / x)) : 90.0;
    }
}
