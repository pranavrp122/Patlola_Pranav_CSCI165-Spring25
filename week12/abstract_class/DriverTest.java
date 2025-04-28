import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Unit tests for Driver.findLargest and Driver.totalArea methods.
 */
public class DriverTest {

    @Test
    public void testFindLargestEmpty() {
        ArrayList<Shape> empty = new ArrayList<>();
        assertNull(Driver.findLargest(empty), "findLargest should return null for empty list");
    }

    @Test
    public void testTotalAreaEmpty() {
        ArrayList<Shape> empty = new ArrayList<>();
        assertEquals(0.0, Driver.totalArea(empty), 1e-9, "totalArea should be 0.0 for empty list");
    }

    @Test
    public void testSingleShape() {
        ArrayList<Shape> shapes = new ArrayList<>();
        Circle c = new Circle(3.0, "blue", true, new Point(0, 0));
        shapes.add(c);

        assertSame(c, Driver.findLargest(shapes), "findLargest should return the only element");
        assertEquals(Math.PI * 3 * 3, Driver.totalArea(shapes), 1e-9, "totalArea should equal the circle's area");
    }

    @Test
    public void testMultipleShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(2.0, "red", false, new Point(0, 0)));   // area = 4π ≈ 12.566
        shapes.add(new Rectangle(2.0, 3.0, "green", false, new Point(0, 0))); // area = 6
        shapes.add(new Square(3.0, "yellow", false, new Point(0, 0)));       // area = 9

        Shape largest = Driver.findLargest(shapes);
        assertTrue(largest instanceof Circle, "Largest should be the circle");
        assertEquals(Math.PI * 4, largest.getArea(), 1e-9, "Largest area should be 4π");

        double expectedTotal = Math.PI * 4 + 6 + 9;
        assertEquals(expectedTotal, Driver.totalArea(shapes), 1e-9, "totalArea should sum all areas");
    }

    @Test
    public void testAfterAddingTriangleAndSemiCircle() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(1.0, "black", false, new Point(0, 0)));                // area = π ≈ 3.14
        shapes.add(new Triangle(3.0, 4.0, 5.0, "purple", false, new Point(0, 0)));   // area = 6
        shapes.add(new SemiCircle(2.0, "pink", false, new Point(0, 0)));             // area = (π*4)/2 = 2π ≈ 6.283

        Shape largest = Driver.findLargest(shapes);
        assertTrue(largest instanceof SemiCircle, "Largest should be the semicircle");

        double expectedTotal = Math.PI * 1 * 1 + 6 + (Math.PI * 4 / 2);
        assertEquals(expectedTotal, Driver.totalArea(shapes), 1e-9, "totalArea should include triangle and semicircle");
    }
}
