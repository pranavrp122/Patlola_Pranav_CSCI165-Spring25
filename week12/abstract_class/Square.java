import java.awt.Color;

/**
 * A Square is a Rectangle with equal sides.
 */
public class Square extends Rectangle {
    /**
     * Full constructor.
     */
    public Square(double side, String colorStr, boolean filled, Point location) {
        super(side, side, colorStr, filled, location);
    }

    /**
     * Minimal constructor (default color/filled/location).
     */
    public Square(double side) {
        super(side, side, "black", false, new Point(0,0));
    }

    /** @return side length */
    public double getSide() { return getWidth(); }

    /** @param side new side (updates both width and length) */
    public void setSide(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public void setWidth(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public void setLength(double side) {
        super.setLength(side);
        super.setWidth(side);
    }

    @Override
    public String toString() {
        return "Square [side=" + getSide() + ", " + super.toString() + "]";
    }
}
