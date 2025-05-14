import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * panel that displays and updates the raymarcher scene
 */
public class RaymarcherPanel extends JPanel {

    /** total number of shapes in the scene */
    private static final int NUM_SHAPES = 15;

    private final RaymarcherRunner raymarcherRunner;
    private List<Shape> shapes;
    private Camera cam;
    private Ray ray;

    /**
     * full constructor.
     * @param raymarcherRunner parent runner instance
     */
    public RaymarcherPanel(RaymarcherRunner raymarcherRunner) {
        // store reference to the runner
        this.raymarcherRunner = raymarcherRunner;

        // determine panel size from frame height
        int size = raymarcherRunner.getFrame().getHeight();
        setPreferredSize(new Dimension(size, size));
        setBackground(Color.BLACK);

        // enable this panel to receive key events
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // prepare the list of shapes
        shapes = new ArrayList<>();
        int perType      = NUM_SHAPES / 3;
        int extra        = NUM_SHAPES % 3;
        int numCircles   = perType + (extra > 0 ? 1 : 0);
        int numRectangles= perType + (extra > 1 ? 1 : 0);
        int numTriangles = perType;

        // add random circles
        for (int i = 0; i < numCircles; i++) {
            Point p = new Point(Math.random() * size, Math.random() * size);
            double r = 20 + Math.random() * 30;
            shapes.add(new Circle(r, randomColor(), true, p));
        }

        // add random rectangles
        for (int i = 0; i < numRectangles; i++) {
            Point p = new Point(Math.random() * size, Math.random() * size);
            double w = 20 + Math.random() * 40;
            double h = 20 + Math.random() * 40;
            shapes.add(new Rectangle(w, h, randomColor(), true, p));
        }

        // add random triangles
        for (int i = 0; i < numTriangles; i++) {
            Point p = new Point(Math.random() * size, Math.random() * size);
            double s1 = randomSide();
            double s2 = randomSide();
            double s3 = randomSide();
            // ensure triangle inequality
            if (s1 + s2 <= s3) s3 = s1 + s2 + 5;
            if (s1 + s3 <= s2) s2 = s1 + s3 + 5;
            if (s2 + s3 <= s1) s1 = s2 + s3 + 5;
            shapes.add(new Triangle(s1, s2, s3, randomColor(), true, p));
        }

        // set up camera at center and register listeners
        cam = new Camera(new Point(size / 2.0, size / 2.0));
        addMouseMotionListener(cam);
        addMouseListener(cam);
        addKeyListener(cam);

        // initialize empty ray
        ray = new Ray();
    }

    /**
     * generate a random hex color string.
     * @return color in format "#rrggbb"
     */
    private String randomColor() {
        return "#" + Integer.toHexString((int)(Math.random() * 0xffffff));
    }

    /**
     * generate a random triangle side length.
     * @return length between 30 and 50
     */
    private double randomSide() {
        return 30 + Math.random() * 20;
    }

    /**
     * ensure this panel has focus when added to its container.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    /**
     * paint the scene: shapes, sphere, ray, and camera.
     * @param g graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // get camera coordinates
        double cx = cam.getCenter().getX();
        double cy = cam.getCenter().getY();

        // draw all shapes
        for (Shape s : shapes) {
            s.drawObject(g2d);
        }

        // compute and draw sphere to nearest shape
        double minD = Double.MAX_VALUE;
        for (Shape s : shapes) {
            minD = Math.min(minD, s.computeDistance(cam.getCenter()));
        }
        g2d.setColor(Color.WHITE);
        g2d.draw(new Ellipse2D.Double(
            cx - minD, cy - minD, minD * 2, minD * 2));

        // build the ray via sphere tracing
        ray.clear();
        double x = cx, y = cy, ang = cam.getAngle();
        for (int i = 0; i < 100; i++) {
            Point p = new Point(x, y);
            double dist = Double.MAX_VALUE;
            for (Shape s : shapes) {
                dist = Math.min(dist, s.computeDistance(p));
            }
            // stop if close enough or too far
            if (dist < 0.01 || dist > 1000) break;
            ray.addMarch(new March(x, y, ang, dist));
            // advance point along the ray
            x += dist * Math.cos(ang);
            y += dist * Math.sin(ang);
        }

        // draw the white ray line
        g2d.setColor(Color.WHITE);
        g2d.draw(new Line2D.Double(cx, cy, x, y));

        // draw all march‐circles
        ray.drawObject(g2d);

        // finally draw the camera
        cam.drawObject(g2d);
    }
}
