import java.awt.Color;

/**
 * Half of a Circle.
 */
public class SemiCircle extends Circle {
    /**
     * Full constructor (radius, color, filled, location).
     */
    public SemiCircle(double radius, String colorStr, boolean filled, Point location) {
        super(radius, colorStr, filled, location);
    }

    @Override
    public double getArea() {
        return super.getArea()/2;
    }

    @Override
    public double getPerimeter() {
        // half of circumference + diameter
        return super.getPerimeter()/2 + 2*getRadius();
    }

    @Override
    public String toString() {
        return "SemiCircle [radius=" + getRadius() + ", " + super.toString() + "]";
    }
}
