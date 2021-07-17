package polymorphism;

public class TriangleEquilateral implements Figure{
    private double sideLength;

    public TriangleEquilateral(float sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getPerimiter() {
        return 3 * sideLength;
    }

    @Override
    public double getArea() {
        return Math.sqrt(3) * sideLength / 4;
    }
}
