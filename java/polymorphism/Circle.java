package polymorphism;

public class Circle implements Figure{
    private double radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    @Override
    public double getPerimiter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
