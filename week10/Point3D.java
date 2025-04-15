package lab;
/**
 * point3d class represents a point in 3d space.
 * it extends point and adds a z coordinate.
 */
public class Point3D extends Point {
    // private variable for z coordinate.
    private int z;

    /**
     * no-arg constructor.
     * sets point to (0,0,0).
     */
    public Point3D() {
        super();
        this.z = 0;
    }

    /**
     * constructor with point and z.
     *
     * @param point a point object for x and y.
     * @param z the z coordinate.
     */
    public Point3D(Point point, int z) {
        super(point);
        this.z = z;
    }

    /**
     * constructor with x, y, and z.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param z the z coordinate.
     */
    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    /**
     * copy constructor.
     *
     * @param other the other point3d.
     */
    public Point3D(Point3D other) {
        super(other);
        this.z = other.z;
    }

    /**
     * get the z coordinate.
     *
     * @return the z value.
     */
    public int getZ() {
        return this.z;
    }

    /**
     * set the z coordinate.
     *
     * @param z the new z value.
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * get all coordinates as a float array.
     *
     * @return a 3 element array of {x, y, z}.
     */
    public float[] getXYZ() {
        return new float[]{(float) getX(), (float) getY(), (float) z};
    }

    /**
     * override toString to show point3d as "(x, y, z)".
     *
     * @return a string description of the point3d.
     */
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + z + ")";
    }

    /**
     * override equals to compare z along with x and y.
     *
     * @param obj the other object to compare.
     * @return true if the points are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        if (obj == null || !(obj instanceof Point3D))
            return false;
        Point3D other = (Point3D) obj;
        return this.z == other.z;
    }
}
