package lab;
/**
 * unittests class contains unit tests for point classes.
 * it tests distance and equals methods.
 */
public class UnitTests {
    public static void main(String[] args) {
        int testsPassed = 0;
        int testsRun = 0;

        // test point distance (using int parameters)
        testsRun++;
        Point p1 = new Point(0, 0);
        double d1 = p1.distance(3, 4); // expected 5.0 (3-4-5 triangle)
        if (Math.abs(d1 - 5.0) < 0.0001) {
            testsPassed++;
            System.out.println("test 1 passed: point.distance(int, int)");
        } else {
            System.out.println("test 1 failed: expected 5.0, got " + d1);
        }

        // test overloaded point distance with point parameter
        testsRun++;
        Point p2 = new Point(3, 4);
        double d2 = p1.distance(p2); // from (0,0) to (3,4)
        if (Math.abs(d2 - 5.0) < 0.0001) {
            testsPassed++;
            System.out.println("test 2 passed: point.distance(point)");
        } else {
            System.out.println("test 2 failed: expected 5.0, got " + d2);
        }

        // test point equals
        testsRun++;
        Point p3 = new Point(3, 4);
        if (p2.equals(p3)) {
            testsPassed++;
            System.out.println("test 3 passed: point.equals");
        } else {
            System.out.println("test 3 failed: points should be equal");
        }

        // test movablepoint move method
        testsRun++;
        MovablePoint mp1 = new MovablePoint(1, 2, 3.0f, 4.0f);
        mp1.move(); // new position should be (4,6)
        if (mp1.getX() == 4 && mp1.getY() == 6) {
            testsPassed++;
            System.out.println("test 4 passed: movablepoint.move");
        } else {
            System.out.println("test 4 failed: expected (4,6), got (" + mp1.getX() + ", " + mp1.getY() + ")");
        }

        // test movablepoint equals
        testsRun++;
        MovablePoint mp2 = new MovablePoint(1, 2, 3.0f, 4.0f);
        MovablePoint mp3 = new MovablePoint(1, 2, 3.0f, 4.0f);
        if (mp2.equals(mp3)) {
            testsPassed++;
            System.out.println("test 5 passed: movablepoint.equals");
        } else {
            System.out.println("test 5 failed: movablepoints should be equal");
        }

        // test point3d distance (inherited from point so only x and y are used)
        testsRun++;
        Point3D p3d1 = new Point3D(3, 4, 5);
        double d3 = p3d1.distance(); // from (3,4) to (0,0) should be 5.0
        if (Math.abs(d3 - 5.0) < 0.0001) {
            testsPassed++;
            System.out.println("test 6 passed: point3d.distance");
        } else {
            System.out.println("test 6 failed: expected 5.0, got " + d3);
        }

        // test point3d equals
        testsRun++;
        Point3D p3d2 = new Point3D(3, 4, 5);
        Point3D p3d3 = new Point3D(3, 4, 5);
        if (p3d2.equals(p3d3)) {
            testsPassed++;
            System.out.println("test 7 passed: point3d.equals");
        } else {
            System.out.println("test 7 failed: point3d objects should be equal");
        }

        // summary of tests
        System.out.println("\nunit tests run - " + testsRun + ", passed - " + testsPassed);
    }
}
