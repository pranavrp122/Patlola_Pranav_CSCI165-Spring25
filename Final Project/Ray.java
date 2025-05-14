import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

/**
 * represents a series of march steps forming a complete ray
 */
public class Ray implements Drawable {
    private List<March> marches;

    /**
     * create an empty ray with no marches
     */
    public Ray() {
        // initialize march list
        this.marches = new ArrayList<>();
    }

    /**
     * add a single march step to this ray
     * @param m march segment to include
     */
    public void addMarch(March m) {
        marches.add(m);
    }

    /**
     * remove all march steps from this ray
     */
    public void clear() {
        marches.clear();
    }

    /**
     * draw every march segment in sequence
     * @param g2d graphics context to draw on
     */
    @Override
    public void drawObject(Graphics2D g2d) {
        // iterate through marches and draw each one
        for (March m : marches) {
            m.drawObject(g2d);
        }
    }
}
