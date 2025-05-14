import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * a single step in the ray march rendered as a circle
 */
public class March implements Drawable {
    private double x, y, angle, length;

    /**
     * full constructor
     * @param x      starting x coordinate
     * @param y      starting y coordinate
     * @param angle  direction in radians
     * @param length march length
     */
    public March(double x, double y, double angle, double length) {
        this.x      = x;
        this.y      = y;
        this.angle  = angle;
        this.length = length;
    }

    /**
     * draw the march as a circle centered at (x,y) with radius length
     */
    @Override
    public void drawObject(Graphics2D g2d) {
        // draw circle for this march step
        g2d.setColor(java.awt.Color.RED);
        g2d.draw(new Ellipse2D.Double(
            x - length,
            y - length,
            length * 2,
            length * 2));
    }
}
