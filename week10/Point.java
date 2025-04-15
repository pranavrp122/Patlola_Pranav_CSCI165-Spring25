package lab;
/**
 * point class models a 2d point.
 * it has x and y coordinates.
 */
public class Point {
    // private variables for x and y.
    private int x;
    private int y;

    /**
     * no-arg constructor.
     * sets point to (0,0).
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * overloaded constructor.
     * sets point to given x and y.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * copy constructor.
     * creates a new point from another point.
     *
     * @param other the other point.
     */
    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * get the x coordinate.
     *
     * @return the x value.
     */
    public int getX() {
        return this.x;
    }

    /**
     * set the x coordinate.
     *
     * @param x the new x value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * get the y coordinate.
     *
     * @return the y value.
     */
    public int getY() {
        return this.y;
    }

    /**
     * set the y coordinate.
     *
     * @param y the new y value.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * set both x and y coordinates.
     *
     * @param x the new x value.
     * @param y the new y value.
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get the coordinates as an int array.
     *
     * @return a 2 element array with x and y.
     */
    public int[] getXY() {
        return new int[]{this.x, this.y};
    }

    /**
     * return string in the format "(x, y)".
     *
     * @return the string description of the point.
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * check if two points are equal.
     *
     * @param obj the other object to compare.
     * @return true if both points have same coordinates.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Point))
            return false;
        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * calculate the distance from this point to the given (x, y).
     *
     * @param x the x coordinate of other point.
     * @param y the y coordinate of other point.
     * @return the euclidean distance.
     */
    public double distance(int x, int y) {
        int dx = this.x - x;
        int dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * overloaded distance method.
     * calculates the distance to another point.
     *
     * @param other the other point.
     * @return the euclidean distance.
     */
    public double distance(Point other) {
        return distance(other.x, other.y);
    }

    /**
     * overloaded distance method.
     * calculates distance from this point to origin (0,0).
     *
     * @return the distance to origin.
     */
    public double distance() {
        return distance(0, 0);
    }

    /**
     * static method to calculate distance between two points.
     *
     * @param a the first point.
     * @param b the second point.
     * @return the euclidean distance between a and b.
     */
    public static double distance(Point a, Point b) {
        return a.distance(b);
    }
}
