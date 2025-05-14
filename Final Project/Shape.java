import java.awt.Color;

/**
 * base class for any 2d object in the world
 * holds color, fill status, and center point
 */
public abstract class Shape implements Drawable {

    /** color string (name or hex) */
    protected String colorStr;
    /** whether the shape is filled or just outlined */
    protected boolean filled;
    /** center or reference point for the shape */
    protected Point center;
    /** decoded Color object for drawing */
    protected Color color;

    /**
     * create a shape with given color, fill status, and center point
     * @param colorStr color name or hex string
     * @param filled   true to fill the shape, false to draw outline
     * @param location center or reference point for the shape
     */
    public Shape(String colorStr, boolean filled, Point location) {
        // store initial properties
        this.colorStr = colorStr;
        this.filled   = filled;
        this.center   = location;
        // try to decode the color string, use black on failure
        try {
            this.color = Color.decode(colorStr);
        } catch (Exception e) {
            // fallback color
            this.color = Color.BLACK;
        }
    }

    /**
     * compute the area of this shape
     * @return the shape's area
     */
    public abstract double getArea();

    /**
     * compute the perimeter (circumference) of this shape
     * @return the shape's perimeter
     */
    public abstract double getPerimeter();

    /**
     * resize the shape by a percentage.
     * @param percent percent change (e.g. 10 increases size by 10%)
     */
    public abstract void resize(int percent);

    /**
     * compute the signed distance from point p to this shape's boundary
     * negative if p is inside the shape, positive if outside
     * @param p point to measure distance from
     * @return signed distance to the shape boundary
     */
    public abstract double computeDistance(Point p);

    /**
     * draw this shape on the graphics context
     * @param g2d graphics context to draw on
     */
    public abstract void drawObject(java.awt.Graphics2D g2d);

    /**
     * get a simple string representation of this shape
     * @return string including color, fill status, and center
     */
    @Override
    public String toString() {
        // example: "color=#ff0000, filled=true, center=Point [x=100.0, y=150.0]"
        return "color=" + colorStr + ", filled=" + filled + ", center=" + center;
    }

    /**
     * check whether this shape equals another object
     * compares class, fill status, color string, and center
     * @param obj object to compare
     * @return true if obj is same type and has identical properties
     */
    @Override
    public boolean equals(Object obj) {
        // same reference?
        if (this == obj) return true;
        // null or different class fails
        if (obj == null || getClass() != obj.getClass()) return false;
        Shape other = (Shape) obj;
        // compare filled flag
        if (filled != other.filled) return false;
        // compare color strings
        if (colorStr == null) {
            if (other.colorStr != null) return false;
        } else if (!colorStr.equals(other.colorStr)) {
            return false;
        }
        // compare center points
        if (center == null) {
            if (other.center != null) return false;
        } else if (!center.equals(other.center)) {
            return false;
        }
        return true;
    }
}
