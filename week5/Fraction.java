/*
 * File:       Fraction.java
 * Author:     Ken Whitener
 * Modified By: Your Name
 * Date:       February 25, 2024
 * 
 * This file contains the definition of a class that represents a fraction.
 * 
 * TASK:      Complete the implementation of the Fraction class as described in the comments below.
 *            Respond to each TO DO comment by implementing the described functionality.
 * 
 *            Add your name to the "Modified By" line above.
 */

 public class Fraction {

    // +============================+
    // | PRIVATE INSTANCE VARIABLES |
    // +============================+
    private int numerator;      // the numerator of the fraction
    private int denominator;    // the denominator of the fraction

    // +==============+
    // | CONSTRUCTORS |
    // +==============+

    /**
     * Fraction constructor that takes 2 arguments
     * 
     * @param n the numerator of the fraction
     * @param d the denominator of the fraction; if d is 0, it is set to 1.
     */
    public Fraction(int n, int d) {
        numerator = n;
        // Use the setter for denominator to perform validation
        setDenominator(d);
        // Optionally, reduce the fraction immediately
        reduce();
    }

    /**
     * Fraction constructor that takes 1 argument.
     * The denominator is initialized to 1.
     * 
     * @param n the numerator of the fraction
     */
    public Fraction(int n) {
        numerator = n;
        setDenominator(1);
        // No need to reduce if denominator is 1.
    }

    // +=========================+
    // | PUBLIC INSTANCE METHODS |
    // +=========================+

    /**
     * Sets the numerator of the fraction.
     * 
     * @param n the new numerator value.
     */
    public void setNumerator(int n) {
        numerator = n;
    }

    /**
     * 
     * @return the numerator of the fraction.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Sets the denominator of the fraction. Denominator cannot be zero.
     * If a zero is provided, the denominator is set to 1.
     * 
     * @param d the new denominator value.
     */
    public void setDenominator(int d) {
        if (d == 0) {
            // Prevent denominator from being zero.
            denominator = 1;
        } else {
            denominator = d;
        }
    }

    /**
     * 
     * @return the denominator of the fraction.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Method to add a fraction to this fraction.
     * Updates this fraction with the result.
     * 
     * @param f the fraction to add.
     */
    public void add(Fraction f) {
        // a/b + c/d = (a*d + c*b) / (b*d)
        numerator = numerator * f.denominator + f.numerator * denominator;
        denominator = denominator * f.denominator;
        reduce();
    }

    /**
     * Subtracts a fraction from this fraction.
     * Updates this fraction with the result.
     * 
     * @param f the fraction to subtract.
     */
    public void subtract(Fraction f) {
        // a/b - c/d = (a*d - c*b) / (b*d)
        numerator = numerator * f.denominator - f.numerator * denominator;
        denominator = denominator * f.denominator;
        reduce();
    }

    /**
     * Multiplies this fraction by another fraction.
     * Updates this fraction with the result.
     * 
     * @param f the fraction to multiply by.
     */
    public void multiply(Fraction f) {
        // (a/b) * (c/d) = (a*c) / (b*d)
        numerator = numerator * f.numerator;
        denominator = denominator * f.denominator;
        reduce();
    }

    /**
     * Divides this fraction by another fraction.
     * Updates this fraction with the result.
     * If the fraction to divide by has a numerator of 0, this fraction remains unchanged.
     * 
     * @param f the fraction to divide by.
     */
    public void divide(Fraction f) {
        // (a/b) / (c/d) = (a*d) / (b*c)
        if (f.numerator == 0) {
            System.out.println("Cannot divide by zero fraction.");
            return;
        }
        numerator = numerator * f.denominator;
        denominator = denominator * f.numerator;
        // Fix potential negative sign if denominator becomes negative
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        reduce();
    }

    /**
     * Reduces the fraction to its lowest terms.
     * This method modifies the fraction by dividing the numerator and denominator by their gcd.
     */
    public void reduce() {
        int g = gcd(Math.abs(numerator), Math.abs(denominator));
        if (g != 0) { // prevent division by zero
            numerator /= g;
            denominator /= g;
        }
        // Ensure the denominator is positive.
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    /**
     * 
     * @return the value of the fraction as a double.
     */
    public double toDecimal() {
        return (double) numerator / denominator;
    }

    /**
     * 
     * @return the value of the fraction as a string in the format "numerator/denominator".
     */
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * Determines if this fraction is equal to another fraction.
     * Equality is determined by cross-multiplication.
     * 
     * @param f the fraction to compare to.
     * @return true if the fractions are equal, false otherwise.
     */
    public boolean equals(Fraction f) {
        // Instead of reducing, we can cross multiply to avoid floating point inaccuracies.
        return (this.numerator * f.denominator) == (f.numerator * this.denominator);
    }

    /**
     * Compares this fraction to another fraction.
     * 
     * @param f the fraction to compare to.
     * @return -1 if this fraction is less than f, 0 if they are equal, 1 if this fraction is greater than f.
     */
    public int compareTo(Fraction f) {
        int lhs = this.numerator * f.denominator;
        int rhs = f.numerator * this.denominator;
        if (lhs > rhs) {
            return 1;
        } else if (lhs < rhs) {
            return -1;
        } else {
            return 0;
        }
    }

    // +=================+
    // | PRIVATE METHODS |
    // +=================+

    /**
     * Calculates the greatest common divisor (gcd) of two integers using the Euclidean algorithm.
     * 
     * @param a the first integer.
     * @param b the second integer.
     * @return the greatest common divisor of a and b.
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}
