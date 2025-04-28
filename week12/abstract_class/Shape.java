import java.awt.Color;

/**
 * Abstract superclass for all shapes.
 * Implements Resizable (resize by percentage).
 */
public abstract class Shape implements Resizable {
    // protected so subclasses can access directly
    protected Color color;
    protected boolean filled;
    protected Point location;

    /**
     * Parse a hex string ("#RRGGBB"/"0xRRGGBB") or name ("red", "blue", etc.)
     * into a java.awt.Color, including purple.
     */
    protected static Color parseColor(String s) {
        try {
            // try hex/decimal form first
            return Color.decode(s);
        } catch (NumberFormatException e) {
            // fallback to named colors
            switch (s.toLowerCase()) {
                case "black":     return Color.BLACK;
                case "blue":      return Color.BLUE;
                case "cyan":      return Color.CYAN;
                case "darkgray":  return Color.DARK_GRAY;
                case "gray":      return Color.GRAY;
                case "green":     return Color.GREEN;
                case "lightgray": return Color.LIGHT_GRAY;
                case "magenta":   return Color.MAGENTA;
                case "orange":    return Color.ORANGE;
                case "pink":      return Color.PINK;
                case "red":       return Color.RED;
                case "white":     return Color.WHITE;
                case "yellow":    return Color.YELLOW;
                case "purple":    return new Color(128, 0, 128);
                default:
                    throw new IllegalArgumentException("Unknown color: " + s);
            }
        }
    }

    /** Constructor taking a Color object. */
    public Shape(Color color, boolean filled, Point location) {
        this.color = color;
        this.filled = filled;
        this.location = location;
    }

    /** Constructor taking a color‐string (hex or name). */
    public Shape(String colorStr, boolean filled, Point location) {
        this(parseColor(colorStr), filled, location);
    }

    // getters & setters
    public Color getColor()        { return color; }
    public void setColor(Color c)  { this.color = c; }

    public boolean isFilled()      { return filled; }
    public void setFilled(boolean f){ this.filled = f; }

    public Point getLocation()     { return location; }
    public void setLocation(Point p){ this.location = p; }

    // abstract methods per lab
    public abstract double getArea();
    public abstract double getPerimeter();

    @Override
    public String toString() {
        return "Shape [color=" + color + ", filled=" + filled + ", location=" + location + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)                return true;
        if (obj == null)                return false;
        if (getClass() != obj.getClass()) return false;
        Shape other = (Shape) obj;
        if (!color.equals(other.color)) return false;
        if (filled != other.filled)     return false;
        if (!location.equals(other.location)) return false;
        return true;
    }
}
