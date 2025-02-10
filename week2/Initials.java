public class Initials {
    public static void main(String[] args) {

        // see if user provided a name as a single command line argument
        if (args.length < 1) {
            System.out.println("Usage: java Initials \"FirstName LastName\"");
            return;
        }

        // full name is the first command line argument.
        String fullName = args[0];

        // find the index of the space that separates the first and last name.
        int spaceIndex = fullName.indexOf(' ');
        if (spaceIndex == -1) {
            System.out.println("Please provide both a first and last name separated by a space.");
            return;
        }

        // extract the first character of the first name
        char firstInitial = fullName.charAt(0);

        // extract the first character of the last name.
        char lastInitial = fullName.charAt(spaceIndex + 1);

        // 1. print the characters individually, each followed by a period
        System.out.println(firstInitial + "." + lastInitial + ".");

        // 2. print the numeric values of the characters by changing them to int
        int firstInitialVal = (int) firstInitial;
        int lastInitialVal = (int) lastInitial;
        System.out.println("T = " + firstInitialVal + ", I = " + lastInitialVal);

        // 3. print the sum of the numeric values.
        int sum = firstInitialVal + lastInitialVal;
        System.out.println(firstInitialVal + " + " + lastInitialVal + " = " + sum);

        // 4. print the two characters concatenated together as a string.
        String initialsConcatenated = "" + firstInitial + lastInitial;
        System.out.println(initialsConcatenated);
    }
}
