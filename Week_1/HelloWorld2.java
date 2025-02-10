public class HelloWorld2 {
    public static void main(String[] args) {

        // Ccncatenate all command line arguments into one string.
        String fullName = "";
        for (String arg : args) {
            fullName += arg + " ";
        }

        fullName = fullName.trim();
        
        // check if the user provided any arguments.
        if (!fullName.isEmpty()) {
            // print
            System.out.println("Hello, " + fullName + " Nice Work processing the arguments");
        } else {
            System.out.println("No name provided. Please run the program with your name as command line arguments.");
        }
        
        // Display system properties.
        System.out.println("Java Class Path: " + System.getProperty("java.class.path"));
        System.out.println("Java Home: " + System.getProperty("java.home"));
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));
        System.out.println("OS Version: " + System.getProperty("os.version"));
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        System.out.println("User Home Directory: " + System.getProperty("user.home"));
        System.out.println("User Account Name: " + System.getProperty("user.name"));
    }
}
