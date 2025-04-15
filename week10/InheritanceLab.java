package lab;
/**
 * inheritancelab class is the driver to test and demonstrate classes.
 * creates point, movablepoint, and point3d objects
 */
public class InheritanceLab {
    public static void main(String[] args) {
        // create point objects
        Point p1 = new Point(); // default (0,0)
        Point p2 = new Point(3, 4);
        Point p3 = new Point(p2); // copy of p2

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p3: " + p3);

        // test distance methods for point
        System.out.println("distance from p2 to (5,6): " + p2.distance(5, 6));
        System.out.println("distance from p2 to p3: " + p2.distance(p3));
        System.out.println("distance from p2 to origin: " + p2.distance());
        System.out.println("static distance between p2 and p3: " + Point.distance(p2, p3));

        // create movablepoint objects
        MovablePoint mp1 = new MovablePoint(new Point(1, 2), 3.0f, 4.0f);
        MovablePoint mp2 = new MovablePoint(5, 6, 1.5f, 2.5f);
        MovablePoint mp3 = new MovablePoint(mp1); // copy of mp1

        System.out.println("mp1: " + mp1);
        System.out.println("mp2: " + mp2);
        System.out.println("mp3: " + mp3);

        // move mp1 and show result
        mp1.move();
        System.out.println("mp1 after move: " + mp1);

        // test distance methods with movablepoint
        System.out.println("distance from mp2 to p2: " + mp2.distance(p2));
        System.out.println("distance from mp1 to mp2: " + mp1.distance(mp2));

        // create point3d objects
        Point3D p3d1 = new Point3D(new Point(12, 34), 6);
        Point3D p3d2 = new Point3D(7, 8, 9);
        Point3D p3d3 = new Point3D(p3d1); // copy of p3d1

        System.out.println("p3d1: " + p3d1);
        System.out.println("p3d2: " + p3d2);
        System.out.println("p3d3: " + p3d3);

        // note: distance compares only x and y (from base class)
        System.out.println("distance from p3d1 to p2: " + p3d1.distance(p2));

        // test equals method
        System.out.println("p2 equals p3: " + p2.equals(p3));       // should be true
        System.out.println("mp1 equals mp3: " + mp1.equals(mp3));      // might be false if mp1 moved
        System.out.println("p3d1 equals p3d3: " + p3d1.equals(p3d3));  // should be true

        // polymorphism with array of points
        Point[] points = new Point[9];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 2);
        points[3] = new MovablePoint(3, 3, 0.5f, 0.5f);
        points[4] = new MovablePoint(4, 4, 1.0f, 1.0f);
        points[5] = new MovablePoint(5, 5, 1.5f, 1.5f);
        points[6] = new Point3D(6, 6, 6);
        points[7] = new Point3D(7, 7, 7);
        points[8] = new Point3D(8, 8, 8);

        System.out.println("\npolymorphic array of points:");
        for (Point p : points) {
            System.out.println(p.toString());
            // calling subclass specific method will cause compile error
            // p.move(); // error if uncommented
        }

        // find the furthest point from origin.
        double maxDistance = 0.0;
        Point furthest = null;
        for (Point p : points) {
            double d = p.distance();
            if (d > maxDistance) {
                maxDistance = d;
                furthest = p;
            }
        }
        System.out.println("\nfurthest point from origin: " + furthest + " with distance: " + maxDistance);

        // polymorphism with object array
        Object[] objects = new Object[9];
        objects[0] = new Point(10, 10);
        objects[1] = new Point3D(11, 11, 11);
        objects[2] = new MovablePoint(12, 12, 2.0f, 2.0f);
        objects[3] = "a string object";
        objects[4] = new Integer(100); // a java api object
        objects[5] = new Double(50.5);
        objects[6] = new Point(13, 13);
        objects[7] = new Point3D(14, 14, 14);
        objects[8] = new MovablePoint(15, 15, 3.0f, 3.0f);

        System.out.println("\npolymorphic array of objects:");
        for (Object obj : objects) {
            System.out.println(obj.toString());
            // calling subclass specific method isnt allowed on object reference.
            // ((MovablePoint)obj).getSpeeds(); // error if uncommented
        }
    }
}
