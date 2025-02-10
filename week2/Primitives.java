public class Primitives {
    public static void main(String[] args) {
        
        // 1. define and initialize all of the primitives and then print them
        byte myByte = 28;

        short myShort = 1024;
        
        int myInt = 40000;
        
        long myLong = 123456789L;
        
        float myFloat = 6.843F;
        
        double myDouble = 2.71828532859;
        
        char charLiteral = 'P';
        char numericLiteral = 19;
        
        boolean myBoolean = true;

        System.out.printf("Byte value: %d%n", myByte);
        System.out.printf("Short value: %d%n", myShort);
        System.out.printf("Int value: %d%n", myInt);
        System.out.printf("Long value: %d%n", myLong);
        System.out.printf("Float value: %.5f%n", myFloat);
        System.out.printf("Double value: %.12f%n", myDouble);
        System.out.printf("Character literal: %c%n", charLiteral);
        System.out.printf("Numeric literal: %c%n", numericLiteral);
        System.out.printf("Boolean value: %b%n", myBoolean);


        // 2. perform implicit widening and explicit narrowing casts 

        // implicit widening
        byte byteVal = 100;              
        int intFromByte = byteVal;         // byte to int
        double doubleFromInt = intFromByte; // int to double
        
        System.out.printf("Byte value: %d, widened to int: %d, then to double: %.1f%n", 
                          byteVal, intFromByte, doubleFromInt);
        
        // explicit narrowing
        double doubleVal = 123.787;
        int intFromDouble = (int) doubleVal; // double to int with a part lost (goes from 123.787 to 123)
        System.out.printf("Double value: %.3f, narrowed to int: %d%n", 
                          doubleVal, intFromDouble);
        
        // 3. Create two variables of type int

        //max and min values for int
        int maxInt = Integer.MAX_VALUE;
        int minInt = Integer.MIN_VALUE;
        System.out.printf("Integer Maximum Value: %d%n", maxInt);
        System.out.printf("Integer Minimum Value: %d%n", minInt);
        
        // 4. Create two variables of type long

        // maximum and minimum Values for long
        long maxLong = Long.MAX_VALUE;
        long minLong = Long.MIN_VALUE;
        System.out.printf("Long Maximum Value: %d%n", maxLong);
        System.out.printf("Long Minimum Value: %d%n", minLong);
        // difference between Integer.MAX_VALUE and Long.MAX_VALUE:
        System.out.printf("Difference: Long.MAX_VALUE (%d) is much greater than Integer.MAX_VALUE (%d)%n",
                          maxLong, maxInt);
        
        // 5. calculate and display powers

        // calculate the square, cube, and fourth power.
        if (args.length < 1) {
            System.out.println("Please enter an integer to be calculated: ");
        } else {
            // Convert the first argument (a String) to an int using parseInt.
            int baseNum = Integer.parseInt(args[0]);
            
            // Calculate powers using Math.pow:
            double square = Math.pow(baseNum, 2);
            double cube = Math.pow(baseNum, 3);
            double fourth = Math.pow(baseNum, 4);
            
            System.out.printf("For the number %d:%n", baseNum);
            System.out.printf("Square: %.0f%n", square);
            System.out.printf("Cube: %.0f%n", cube);
            System.out.printf("Fourth power: %.0f%n", fourth);
        }
        
        // 6. add ability to process dividend and divisor.

        // need three arguments, one for power calculations and two for division.
        if (args.length < 3) {
            System.out.println("Please provide two more arguments: a dividend, and divisor>");
        } else {
            int dividend = Integer.parseInt(args[1]);
            int divisor = Integer.parseInt(args[2]);
            
            // division and modulus using / and % operators
            int standardDiv = dividend / divisor;
            int standardMod = dividend % divisor;
            
            // floor division and floor modulus
            int floorDiv = Math.floorDiv(dividend, divisor);
            int floorMod = Math.floorMod(dividend, divisor);
            
            System.out.printf("Standard division: %d / %d = %d with remainder %d%n", 
                              dividend, divisor, standardDiv, standardMod);
            System.out.printf("Floor division: Math.floorDiv(%d, %d) = %d, Math.floorMod(%d, %d) = %d%n", 
                              dividend, divisor, floorDiv, dividend, divisor, floorMod);
    }

    }
}
