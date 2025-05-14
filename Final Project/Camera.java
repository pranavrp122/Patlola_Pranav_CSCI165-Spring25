import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/**
 * camera follows mouse, arrow keys, or clicks and holds current angle
 */
public class Camera extends MouseAdapter implements Drawable, KeyListener {
    private Point center;
    private double angle;
    private static final double RADIUS     = 10;                // circle radius in px
    private static final double ANGLE_STEP = Math.toRadians(10); // rotation step in radians

    /**
     * create a camera at given start position
     * @param start initial camera position
     */
    public Camera(Point start) {
        this.center = start;
        this.angle  = 0;
    }

    /**
     * update camera position when mouse moves
     * @param e mouse event containing new coords
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // move camera center to mouse location
        center.setX(e.getX());
        center.setY(e.getY());
    }

    /**
     * rotate camera on mouse clicks
     * @param e mouse button event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // left click rotates clockwise
        if (e.getButton() == MouseEvent.BUTTON1) {
            angle += ANGLE_STEP;
        }
        // right click rotates counter-clockwise
        else if (e.getButton() == MouseEvent.BUTTON3) {
            angle -= ANGLE_STEP;
        }
    }

    /**
     * rotate camera using arrow keys
     * @param e key event for arrow press
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // left arrow now increases angle
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            angle += ANGLE_STEP;
        }
        // right arrow now decreases angle
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            angle -= ANGLE_STEP;
        }
    }

    /** required by KeyListener but unused */
    @Override public void keyReleased(KeyEvent e) { }

    /** required by KeyListener but unused */
    @Override public void keyTyped(KeyEvent e)    { }

    /**
     * get current camera center point
     * @return center as Point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * get current angle in radians
     * @return angle in radians
     */
    public double getAngle() {
        return angle;
    }

    /**
     * draw the camera as a white filled circle
     * @param g2d graphics context to draw on
     */
    @Override
    public void drawObject(Graphics2D g2d) {
        // draw a white circle representing the camera
        g2d.setColor(java.awt.Color.WHITE);
        g2d.fill(new Ellipse2D.Double(
            center.getX() - RADIUS,
            center.getY() - RADIUS,
            RADIUS * 2,
            RADIUS * 2));
    }
}
