import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Driver {

    /**
     * @param args command line arguments
     */
    public static void main(String[] args) {

        System.out.println("=== Testing Fraction Constructors ===");
        
        // create a fraction 15/25, which should reduce to 3/5
        Fraction f1 = new Fraction(15, 25);
        System.out.println("Fraction f1 (15/25) reduced: " + f1);
        
        // demonstrate handling of an invalid denominator 0, denominator should be set to 1
        Fraction f2 = new Fraction(10, 0);
        System.out.println("Fraction f2 (10/0) handled: " + f2);
        
        // denominator should default to 1
        Fraction f3 = new Fraction(5);
        System.out.println("Fraction f3 (5) is: " + f3);
        
        // create a fraction with both negative numerator and denominator
        Fraction f4 = new Fraction(-8, -4);  // should reduce to 2/1
        System.out.println("Fraction f4 (-8/-4) reduced: " + f4);
        

        System.out.println("\n=== Testing Arithmetic Methods ===");
        
        Fraction a = new Fraction(1, 2); // 1/2
        Fraction b = new Fraction(1, 3); // 1/3
        System.out.println("Initial fractions: a = " + a + ", b = " + b);
        
        // test addition 1/2 + 1/3 = 5/6
        a.add(b);
        System.out.println("After a.add(b), a = " + a);
        

        a = new Fraction(1, 2);
        a.subtract(b); // 1/2 - 1/3 = 1/6
        System.out.println("After a.subtract(b), a = " + a);
        
        // test multiplication 2/3 * 1/3 = 2/9
        a = new Fraction(2, 3);
        a.multiply(b);
        System.out.println("After a.multiply(b), a = " + a);
        
        // test division (3/4) / (2/5) = 15/8
        a = new Fraction(3, 4);
        b = new Fraction(2, 5);
        a.divide(b);
        System.out.println("After a.divide(b), a = " + a);
        

        System.out.println("\n=== Testing toDecimal, equals, and compareTo ===");
        System.out.println("Fraction f1 as decimal: " + f1.toDecimal());
        
        Fraction c1 = new Fraction(2, 4); // should reduce to 1/2
        Fraction c2 = new Fraction(1, 2);
        System.out.println("c1: " + c1 + ", c2: " + c2);
        System.out.println("c1.equals(c2): " + c1.equals(c2));
        System.out.println("c1.compareTo(c2): " + c1.compareTo(c2));
        
        
        System.out.println("\n=== Reading Fractions from file fractions.txt ===");
        Fraction[] fractions = new Fraction[100];
        int count = 0;
        try {
            Scanner fileScanner = new Scanner(new File("fractions.txt"));
            while (fileScanner.hasNext() && count < fractions.length) {
                int num = fileScanner.nextInt();
                int den = fileScanner.nextInt();
                fractions[count] = new Fraction(num, den);
                count++;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File fractions.txt not found.");
        }
        
        System.out.println("Fractions read from file:");
        for (int i = 0; i < count; i++) {
            System.out.println("Fraction " + i + ": " + fractions[i]);
        }
        
        
        System.out.println("\n=== Finding Largest Fraction in the Array ===");
        Fraction largest = largestFraction(fractions);
        if (largest != null) {
            System.out.println("The largest fraction is: " + largest);
        } else {
            System.out.println("No fractions were loaded.");
        }
    }

    /**
     * @param fractions an array of Fraction objects.
     * @return the largest Fraction found in the array; null if the array is empty.
     */
    public static Fraction largestFraction(Fraction[] fractions) {
        if (fractions == null || fractions.length == 0) {
            return null;
        }
        Fraction largest = fractions[0];
        for (Fraction f : fractions) {
            if (f != null && f.compareTo(largest) > 0) {
                largest = f;
            }
        }
        return largest;
    }
}
