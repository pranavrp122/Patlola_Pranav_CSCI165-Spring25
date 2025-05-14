import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * a concrete circle subclass that always draws filled
 */
public class Circle extends Shape {
    private double radius;

    /**
     * full constructor.
     * @param radius   circle radius in pixels
     * @param colorStr color name or hex string
     * @param filled   fill status (ignored, always filled)
     * @param location center point of the circle
     */
    public Circle(double radius, String colorStr, boolean filled, Point location) {
        super(colorStr, true, location);
        this.radius = radius;
    }

    /**
     * compute the area of this circle
     * @return area = π * r²
     */
    @Override
    public double getArea() {
        // area calculation
        return Math.PI * radius * radius;
    }

    /**
     * compute the perimeter (circumference) of this circle
     * @return perimeter = 2 * π * r
     */
    @Override
    public double getPerimeter() {
        // perimeter calculation
        return 2 * Math.PI * radius;
    }

    /**
     * resize this circle by a given percentage
     * @param percent percent change (e.g. 10 increases radius by 10%)
     */
    @Override
    public void resize(int percent) {
        // adjust radius by factor
        radius *= 1 + percent / 100.0;
    }

    /**
     * compute the signed distance from point p to the circle's edge
     * negative if inside, positive if outside
     * @param p point to test
     * @return signed distance to edge
     */
    @Override
    public double computeDistance(Point p) {
        // distance from center minus radius
        double dx = p.getX() - center.getX();
        double dy = p.getY() - center.getY();
        double distToCenter = Math.sqrt(dx * dx + dy * dy);
        return distToCenter - radius;
    }

    /**
     * draw this circle on the graphics context
     * always draws a filled circle of the given radius
     * @param g2d graphics context
     */
    @Override
    public void drawObject(Graphics2D g2d) {
        // set color and draw filled ellipse
        g2d.setColor(color);
        g2d.fill(new Ellipse2D.Double(
            center.getX() - radius,
            center.getY() - radius,
            radius * 2,
            radius * 2));
    }
}
