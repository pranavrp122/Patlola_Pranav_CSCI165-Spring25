import java.awt.Color;

/**
 * A concrete Rectangle subclass.
 */
public class Rectangle extends Shape {
    private double width, length;

    /**
     * Full constructor.
     * @param w        width
     * @param l        length
     * @param colorStr color name or hex
     * @param filled   fill status
     * @param location top-left point
     */
    public Rectangle(double w, double l, String colorStr, boolean filled, Point location) {
        super(colorStr, filled, location);
        this.width = w;
        this.length = l;
    }

    /** @return width */
    public double getWidth()  { return width; }
    /** @return length */
    public double getLength() { return length; }
    /** @param w new width */
    public void setWidth(double w)  { width  = w; }
    /** @param l new length */
    public void setLength(double l) { length = l; }

    @Override
    public double getArea()      { return width * length; }
    @Override
    public double getPerimeter() { return 2 * (width + length); }

    @Override
    public void resize(int percent) {
        width  *= 1 + percent/100.0;
        length *= 1 + percent/100.0;
    }

    @Override
    public String toString() {
        return "Rectangle [width=" + width + ", length=" + length + ", " + super.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))           return false;
        Rectangle other = (Rectangle) obj;
        return Double.compare(width, other.width) == 0
            && Double.compare(length, other.length) == 0;
    }
}
