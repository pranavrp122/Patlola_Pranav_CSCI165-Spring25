package lab;
/**
 * movablepoint class represents a point that can move.
 * it extends point and adds speed in x and y.
 */
public class MovablePoint extends Point {
    // private variables for speed.
    private float xSpeed;
    private float ySpeed;

    /**
     * no-arg constructor.
     * calls super and sets speeds to 0.
     */
    public MovablePoint() {
        super();
        this.xSpeed = 0.0f;
        this.ySpeed = 0.0f;
    }

    /**
     * constructor with point and speeds.
     *
     * @param point  a point object for x and y.
     * @param xSpeed the speed in x direction.
     * @param ySpeed the speed in y direction.
     */
    public MovablePoint(Point point, float xSpeed, float ySpeed) {
        super(point);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * constructor with x, y, and speeds.
     *
     * @param x      the x coordinate.
     * @param y      the y coordinate.
     * @param xSpeed the speed in x direction.
     * @param ySpeed the speed in y direction.
     */
    public MovablePoint(int x, int y, float xSpeed, float ySpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * copy constructor.
     *
     * @param other the other movablepoint.
     */
    public MovablePoint(MovablePoint other) {
        super(other);
        this.xSpeed = other.xSpeed;
        this.ySpeed = other.ySpeed;
    }

    /**
     * get the x speed.
     *
     * @return the x speed.
     */
    public float getXSpeed() {
        return this.xSpeed;
    }

    /**
     * set the x speed.
     *
     * @param xSpeed the new x speed.
     */
    public void setXSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * get the y speed.
     *
     * @return the y speed.
     */
    public float getYSpeed() {
        return this.ySpeed;
    }

    /**
     * set the y speed.
     *
     * @param ySpeed the new y speed.
     */
    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * set both speeds.
     *
     * @param xSpeed the new x speed.
     * @param ySpeed the new y speed.
     */
    public void setSpeed(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    /**
     * get speeds as a float array.
     *
     * @return a 2 element array with xSpeed and ySpeed.
     */
    public float[] getSpeeds() {
        return new float[]{this.xSpeed, this.ySpeed};
    }

    /**
     * move the point by adding speeds to coordinates.
     * note: uses getters and setters to update values.
     *
     * @return this movablepoint after move.
     */
    public MovablePoint move() {
        // update x and y by speed.
        setX(getX() + (int) xSpeed);
        setY(getY() + (int) ySpeed);
        return this;
    }

    /**
     * override toString to show point and speed.
     *
     * @return string in the format "(x, y): speed = (xSpeed, ySpeed)".
     */
    @Override
    public String toString() {
        return super.toString() + ": speed = (" + this.xSpeed + ", " + this.ySpeed + ")";
    }

    /**
     * override equals to compare speeds along with coordinates.
     *
     * @param obj the other object to compare.
     * @return true if all values are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (obj == null || !(obj instanceof MovablePoint))
            return false;
        MovablePoint other = (MovablePoint) obj;
        return this.xSpeed == other.xSpeed && this.ySpeed == other.ySpeed;
    }
}
