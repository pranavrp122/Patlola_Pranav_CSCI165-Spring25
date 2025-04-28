import java.awt.Color;

/**
 * A concrete Circle subclass.
 */
public class Circle extends Shape {
    private double radius;

    /**
     * Full constructor.
     * @param radius   circle radius
     * @param colorStr color name or hex string
     * @param filled   fill status
     * @param location center point
     */
    public Circle(double radius, String colorStr, boolean filled, Point location) {
        super(colorStr, filled, location);
        this.radius = radius;
    }

    /** @return radius */
    public double getRadius() { return radius; }
    /** @param r new radius */
    public void setRadius(double r)    { this.radius = r; }

    @Override
    public double getArea()      { return Math.PI * radius * radius; }
    @Override
    public double getPerimeter() { return 2 * Math.PI * radius; }

    @Override
    public void resize(int percent) {
        radius *= 1 + percent/100.0;
    }

    @Override
    public String toString() {
        return "Circle [radius=" + radius + ", " + super.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))           return false;
        Circle other = (Circle) obj;
        return Double.doubleToLongBits(radius)
             == Double.doubleToLongBits(other.radius);
    }
}
