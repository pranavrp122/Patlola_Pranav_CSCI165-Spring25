import java.awt.Color;

/**
 * A 3-sided triangle.
 */
public class Triangle extends Shape {
    private double side1, side2, side3;

    /**
     * Full constructor; enforces triangle inequality.
     */
    public Triangle(double s1, double s2, double s3, String colorStr, boolean filled, Point location) {
        super(colorStr, filled, location);
        if (s1 + s2 <= s3 || s1 + s3 <= s2 || s2 + s3 <= s1)
            throw new IllegalArgumentException("Invalid triangle sides");
        this.side1 = s1;
        this.side2 = s2;
        this.side3 = s3;
    }

    public double getSide1() { return side1; }
    public double getSide2() { return side2; }
    public double getSide3() { return side3; }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public double getArea() {
        double s = getPerimeter()/2;
        return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
    }

    @Override
    public void resize(int percent) {
        double f = 1 + percent/100.0;
        side1 *= f;
        side2 *= f;
        side3 *= f;
    }

    @Override
    public String toString() {
        return "Triangle [side1=" + side1 + ", side2=" + side2 +
               ", side3=" + side3 + ", " + super.toString() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))        return false;
        Triangle other = (Triangle) obj;
        return Double.compare(side1, other.side1)==0
            && Double.compare(side2, other.side2)==0
            && Double.compare(side3, other.side3)==0;
    }
}
