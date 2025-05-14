import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * a concrete triangle subclass defined by three side lengths
 * always draws filled if the filled flag is true
 */
public class Triangle extends Shape {
    private double side1, side2, side3;
    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];

    /**
     * full constructor; enforces triangle inequality and computes vertices
     * @param s1       length of side 1
     * @param s2       length of side 2
     * @param s3       length of side 3
     * @param colorStr color name or hex string
     * @param filled   whether the triangle should be filled
     * @param location reference point for vertex 0
     */
    public Triangle(double s1, double s2, double s3, String colorStr, boolean filled, Point location) {
        super(colorStr, filled, location);
        // ensure valid triangle sides
        if (s1 + s2 <= s3 || s1 + s3 <= s2 || s2 + s3 <= s1)
            throw new IllegalArgumentException("invalid triangle sides");
        this.side1 = s1;
        this.side2 = s2;
        this.side3 = s3;

        // compute vertex coordinates based on the reference point
        double x0 = location.getX();
        double y0 = location.getY();
        // vertex 0 at reference location
        xPoints[0] = (int) Math.round(x0);
        yPoints[0] = (int) Math.round(y0);
        // vertex 1: to the right by side1
        xPoints[1] = (int) Math.round(x0 + side1);
        yPoints[1] = (int) Math.round(y0);
        // vertex 2: use law of cosines to find angle and position
        double cosG = (s1*s1 + s2*s2 - s3*s3) / (2 * s1 * s2);
        double sinG = Math.sqrt(Math.max(0, 1 - cosG * cosG));
        xPoints[2] = (int) Math.round(x0 + s2 * cosG);
        yPoints[2] = (int) Math.round(y0 - s2 * sinG);
    }

    /**
     * compute the area using Heron's formula
     * @return area of the triangle
     */
    @Override
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    /**
     * compute the perimeter (sum of all sides)
     * @return perimeter of the triangle
     */
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    /**
     * resize the triangle by scaling side lengths
     * note: vertex positions are not updated automatically.
     * @param percent percent to change sizes by (e.g., 10 → +10%)
     */
    @Override
    public void resize(int percent) {
        double factor = 1 + percent / 100.0;
        side1 *= factor;
        side2 *= factor;
        side3 *= factor;
        // if resize is used, vertex arrays would need recomputing
    }

    /**
     * compute the signed distance from point p to the triangle edges
     * negative if inside, positive if outside.
     * @param p point to measure
     * @return signed distance to the triangle boundary
     */
    @Override
    public double computeDistance(Point p) {
        double px = p.getX(), py = p.getY();
        // distance to each edge segment
        double d0 = distToSegment(px, py, xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        double d1 = distToSegment(px, py, xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
        double d2 = distToSegment(px, py, xPoints[2], yPoints[2], xPoints[0], yPoints[0]);
        double minD = Math.min(d0, Math.min(d1, d2));

        // check if point is inside triangle using Polygon.contains
        Polygon poly = new Polygon(xPoints, yPoints, 3);
        boolean inside = poly.contains(px, py);

        // return negative inside, positive outside
        return inside ? -minD : minD;
    }

    /**
     * helper to compute distance from a point to a line segment
     * @param px  x of point
     * @param py  y of point
     * @param x1  x of segment start
     * @param y1  y of segment start
     * @param x2  x of segment end
     * @param y2  y of segment end
     * @return shortest distance from point to segment
     */
    private double distToSegment(double px, double py,
                                 double x1, double y1,
                                 double x2, double y2) {
        double vx = x2 - x1, vy = y2 - y1;
        double wx = px - x1, wy = py - y1;
        double c1 = vx * wx + vy * wy;
        if (c1 <= 0) {
            // before start point
            return Math.hypot(px - x1, py - y1);
        }
        double c2 = vx * vx + vy * vy;
        if (c2 <= c1) {
            // beyond end point
            return Math.hypot(px - x2, py - y2);
        }
        // projection onto segment
        double b = c1 / c2;
        double projx = x1 + b * vx;
        double projy = y1 + b * vy;
        return Math.hypot(px - projx, py - projy);
    }

    /**
     * draw this triangle on the graphics context
     * uses fill or draw depending on filled flag
     * @param g2d graphics context to draw on
     */
    @Override
    public void drawObject(Graphics2D g2d) {
        Polygon poly = new Polygon(xPoints, yPoints, 3);
        g2d.setColor(color);
        if (filled) {
            // draw filled triangle
            g2d.fill(poly);
        } else {
            // draw triangle outline
            g2d.draw(poly);
        }
    }
}
