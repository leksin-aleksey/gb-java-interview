package polymorphism;

public class Rectangle implements Figure{
    private double sideLength;

    public Rectangle(float sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getPerimiter() {
        return 4 * sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }
}
