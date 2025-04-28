import java.awt.Color;
import java.util.ArrayList;

/**
 * Driver to demonstrate Tasks Two–Six for the Shape lab.
 */
public class Driver {
    /**
     * Task Three: find the shape with the largest area (no casting).
     * <p>
     * This method loops through each Shape reference and invokes getArea().
     * At runtime, Java's dynamic dispatch calls the correct subclass implementation
     * of getArea() (e.g., Circle, Rectangle, etc.) without any type casting.
     * </p>
     */
    public static Shape findLargest(ArrayList<Shape> shapes) {
        if (shapes == null || shapes.isEmpty()) return null;
        // Start with the first shape as the largest
        Shape largest = shapes.get(0);
        for (Shape s : shapes) {
            // s.getArea() calls the subclass's getArea() implementation
            if (s.getArea() > largest.getArea()) {
                largest = s;
            }
        }
        return largest;
    }

    /**
     * Task Three: total area of all shapes (no casting).
     * <p>
     * This method accumulates the area of each Shape by calling getArea() on
     * the Shape reference. Polymorphism ensures the correct area calculation
     * for each concrete subclass.
     * </p>
     */
    public static double totalArea(ArrayList<Shape> shapes) {
        double total = 0;
        for (Shape s : shapes) {
            // Polymorphic call, no need to know the specific subclass
            total += s.getArea();
        }
        return total;
    }

    /**
     * Task Six: resize all Resizable objects.
     */
    public static void resize(ArrayList<Resizable> resizables, int percent) {
        System.out.println("Before resize:");
        for (Resizable r : resizables) {
            System.out.println(r);
        }
        for (Resizable r : resizables) {
            r.resize(percent);
        }
        System.out.println("\nAfter " + percent + "% resize:");
        for (Resizable r : resizables) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
        Point p = new Point(1, 2);

        System.out.println("--- TASK TWO: Testing statements involving polymorphism ---");

        // 1) Upcast Circle to Shape
        Shape s1 = new Circle(5.5, "red", false, p);
        System.out.println(s1);                 // Circle.toString()
        System.out.println(s1.getArea());       // Circle.getArea()
        System.out.println(s1.getPerimeter());  // Circle.getPerimeter()
        System.out.println(s1.getColor());      // Shape.getColor()
        System.out.println(s1.isFilled());      // Shape.isFilled()
        // System.out.println(s1.getRadius());  // ERROR: getRadius() not in Shape; compile-time: cannot find symbol getRadius()

        // 2) Downcast back to Circle
        Circle c1 = (Circle) s1;
        System.out.println(c1);                 // Circle.toString()
        System.out.println(c1.getArea());       // Circle.getArea()
        System.out.println(c1.getPerimeter());  // Circle.getPerimeter()
        System.out.println(c1.getColor());      // Shape.getColor()
        System.out.println(c1.isFilled());      // Shape.isFilled()
        System.out.println(c1.getRadius());     // Circle.getRadius()

        // 3) Upcast Rectangle to Shape
        Shape s3 = new Rectangle(1.0, 2.0, "red", false, p);
        System.out.println(s3);                 // Rectangle.toString()
        System.out.println(s3.getArea());       // Rectangle.getArea()
        System.out.println(s3.getPerimeter());  // Rectangle.getPerimeter()
        System.out.println(s3.getColor());      // Shape.getColor()
        // System.out.println(s3.getLength());  // ERROR: getLength() not in Shape; compile-time: cannot find symbol getLength()

        // 4) Downcast to Rectangle
        Rectangle r1 = (Rectangle) s3;
        System.out.println(r1);                 // Rectangle.toString()
        System.out.println(r1.getArea());       // Rectangle.getArea()
        System.out.println(r1.getColor());      // Shape.getColor()
        System.out.println(r1.getLength());     // Rectangle.getLength()

        // 5) Upcast Square to Shape
        Shape s4 = new Square(6.6);
        System.out.println(s4);                 // Square.toString()
        System.out.println(s4.getArea());       // Rectangle.getArea() via inheritance
        System.out.println(s4.getColor());      // Shape.getColor()
        // System.out.println(s4.getSide());    // ERROR: getSide() not in Shape; compile-time: cannot find symbol getSide()

        // 6) Downcast to Rectangle (Square upcast to Rectangle)
        Rectangle r2 = (Rectangle) s4;
        System.out.println(r2);                 // Square.toString() via polymorphism
        System.out.println(r2.getArea());       // Rectangle.getArea()
        System.out.println(r2.getColor());      // Shape.getColor()
        // System.out.println(r2.getSide());    // ERROR: getSide() not in Rectangle; compile-time: cannot find symbol getSide()
        System.out.println(r2.getLength());     // Rectangle.getLength()

        // 7) Downcast Rectangle to Square
        Square sq1 = (Square) r2;
        System.out.println(sq1);                // Square.toString()
        System.out.println(sq1.getArea());      // Rectangle.getArea() via inheritance
        System.out.println(sq1.getColor());     // Shape.getColor()
        System.out.println(sq1.getSide());      // Square.getSide()
        System.out.println(sq1.getLength());    // Rectangle.getLength()

        // 8) Instantiate Shape via anonymous subclass
        // Shape s2 = new Shape("blue", true, p); // ERROR: Shape is abstract; compile-time: cannot instantiate abstract class

        // TASK THREE: Testing findLargest and totalArea
        System.out.println("\n--- TASK THREE: Testing findLargest and totalArea ---");
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5.0, "red", true, new Point(0, 0)));
        shapes.add(new Rectangle(4.0, 6.0, "blue", true, new Point(1, 1)));
        shapes.add(new Square(7.0, "green", false, new Point(2, 2)));
        System.out.println("Largest: " + findLargest(shapes));
        System.out.println("Total area: " + totalArea(shapes));

        // TASK FOUR: Adding Triangle
        System.out.println("\n--- TASK FOUR: Adding Triangle ---");
        shapes.add(new Triangle(3.0, 4.0, 5.0, "purple", true, new Point(3, 3)));
        System.out.println("Largest now: " + findLargest(shapes));
        System.out.println("Total area now: " + totalArea(shapes));

        // TASK FIVE: Adding SemiCircle
        System.out.println("\n--- TASK FIVE: Adding SemiCircle ---");
        shapes.add(new SemiCircle(10.0, "yellow", true, new Point(4, 4)));
        System.out.println("Largest now: " + findLargest(shapes));
        System.out.println("Total area now: " + totalArea(shapes));

        // TASK SIX: Testing resize
        System.out.println("\n--- TASK SIX: Testing resize ---");
        ArrayList<Resizable> resizables = new ArrayList<>();
        for (Shape s : shapes) {
            resizables.add(s);
        }
        resize(resizables, 50);
    }
}
