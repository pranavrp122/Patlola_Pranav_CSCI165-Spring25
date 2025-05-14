import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * a rectangle subclass centered on a point
 */
public class Rectangle extends Shape {
    private double width, length;

    /**
     * full constructor
     * @param w        width of the rectangle
     * @param l        length of the rectangle
     * @param colorStr color name or hex string
     * @param filled   whether the rectangle is filled
     * @param center   center point of the rectangle
     */
    public Rectangle(double w, double l, String colorStr, boolean filled, Point center) {
        super(colorStr, filled, center);
        this.width  = w;
        this.length = l;
    }

    /**
     * compute the area of this rectangle
     * @return width * length
     */
    @Override
    public double getArea() {
        // area calculation
        return width * length;
    }

    /**
     * compute the perimeter of this rectangle
     * @return 2 * (width + length)
     */
    @Override
    public double getPerimeter() {
        // perimeter calculation
        return 2 * (width + length);
    }

    /**
     * resize this rectangle by a percentage
     * @param percent percent change (e.g. 10 increases size by 10%)
     */
    @Override
    public void resize(int percent) {
        // adjust size by factor
        double factor = 1 + percent / 100.0;
        width  *= factor;
        length *= factor;
    }

    /**
     * compute the signed distance from point p to this rectangle
     * negative if inside, positive if outside
     * @param p point to test
     * @return signed distance to the rectangle boundary
     */
    @Override
    public double computeDistance(Point p) {
        // get point coords
        double px = p.getX(), py = p.getY();
        // half-dimensions for centering
        double halfW = width  / 2;
        double halfH = length / 2;
        // rectangle bounds
        double left   = center.getX() - halfW;
        double right  = center.getX() + halfW;
        double top    = center.getY() - halfH;
        double bottom = center.getY() + halfH;

        // distance outside along x and y
        double dx = Math.max(Math.max(left - px, 0), px - right);
        double dy = Math.max(Math.max(top  - py, 0), py - bottom);
        if (dx > 0 || dy > 0) {
            // outside region: euclidean distance to nearest edge
            return Math.hypot(dx, dy);
        }

        // inside region: negative distance to nearest edge
        double minX = Math.min(px - left,   right - px);
        double minY = Math.min(py - top,    bottom - py);
        return -Math.min(minX, minY);
    }

    /**
     * draw this rectangle centered at its center point.
     * @param g2d graphics context to draw on
     */
    @Override
    public void drawObject(Graphics2D g2d) {
        // compute top-left corner for centered drawing
        double x = center.getX() - width  / 2;
        double y = center.getY() - length / 2;
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, length);
        // set draw color
        g2d.setColor(color);
        if (filled) {
            // draw filled rectangle
            g2d.fill(rect);
        } else {
            // draw rectangle outline
            g2d.draw(rect);
        }
    }
}
