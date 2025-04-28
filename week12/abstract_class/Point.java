public class Point {
    private int x;
    private int y;

    /** Constructor for point */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** @return x‐coordinate */
    public int getX() { return x; }
    /** @return y‐coordinate */
    public int getY() { return y; }
    /** @param x new x‐coordinate */
    public void setX(int x) { this.x = x; }
    /** @param y new y‐coordinate */
    public void setY(int y) { this.y = y; }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)                return true;
        if (obj == null)                return false;
        if (getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }
}
