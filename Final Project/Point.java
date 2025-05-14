/**
 * represents a point in 2d space
 */
public class Point {
    private double x;
    private double y;

    /**
     * create a point at the given coordinates
     * @param x initial x value
     * @param y initial y value
     */
    public Point(double x, double y) {
        // set initial coordinates
        this.x = x;
        this.y = y;
    }

    /**
     * get the x-coordinate of this point
     * @return x value
     */
    public double getX() {
        // return current x
        return x;
    }

    /**
     * get the y-coordinate of this point
     * @return y value
     */
    public double getY() {
        // return current y
        return y;
    }

    /**
     * update the x-coordinate of this point
     * @param x new x value
     */
    public void setX(double x) {
        // set x to new value
        this.x = x;
    }

    /**
     * update the y-coordinate of this point
     * @param y new y value
     */
    public void setY(double y) {
        // set y to new value
        this.y = y;
    }

    /**
     * get a string representation of this point
     * @return string in format Point [x=..., y=...]
     */
    @Override
    public String toString() {
        // build string for debugging
        return "Point [x=" + x + ", y=" + y + "]";
    }

    /**
     * check if this point equals another object
     * @param obj object to compare
     * @return true if obj is a Point with same coordinates
     */
    @Override
    public boolean equals(Object obj) {
        // same reference?
        if (this == obj) return true;
        // null or different class?
        if (obj == null || getClass() != obj.getClass()) return false;
        // cast and compare values
        Point other = (Point) obj;
        return Double.compare(x, other.x) == 0
            && Double.compare(y, other.y) == 0;
    }
}
