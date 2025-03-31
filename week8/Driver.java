import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * driver class for food truck application
 * runs a single order
 */
public class Driver {

    public static void main(String[] args) {
        // call loadmenuitems and store in a list
        ArrayList<MenuItem> menu = loadMenuItems("products.txt");
        
        // print the menu to the console
        printMenu(menu);
        
        // create a date object with current date values
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1; // calendar month is 0 based
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        Date today = new Date(month, day, year);
        
        // prompt the user for customer information
        Scanner input = new Scanner(System.in);
        System.out.println("enter customer name (first and last):");
        String name = input.nextLine();
        System.out.println("enter customer email:");
        String email = input.nextLine();
        System.out.println("enter customer phone (10 digits):");
        String phone = input.nextLine();
        Customer customer = new Customer(name, email, phone);
        
        // allow customer to choose menu items
        ArrayList<MenuItem> order = new ArrayList<MenuItem>();
        System.out.println("enter the number of the item to add to order or -1 to finish:");
        int choice = 0;
        while (true) {
            System.out.print("your choice: ");
            if(input.hasNextInt()){
                choice = input.nextInt();
            } else {
                input.next(); // clear invalid input
                continue;
            }
            if(choice == -1){
                break;
            }
            // check if choice is within bounds
            if (choice >= 1 && choice <= menu.size()) {
                // add the selected item (subtract 1 for index)
                order.add(menu.get(choice - 1));
                System.out.println("item added.");
            } else {
                System.out.println("invalid choice, try again.");
            }
        }
        input.close();
        
        // print the order receipt to console and file
        printOrder(order, customer, today);
    }

    /**
     * loads menu items from a file
     * @param filename the file to load from
     * @return an arraylist of menu items
     */
    public static ArrayList<MenuItem> loadMenuItems(String filename) {
        ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>(); // create list
        try {
            File file = new File(filename); // create file object
            Scanner scanner = new Scanner(file); // create scanner for file
            while (scanner.hasNextLine()) { // loop through each line
                String line = scanner.nextLine();
                // split line by comma
                String[] tokens = line.split(",");
                if(tokens.length == 3) {
                    String name = tokens[0].trim();
                    double price = Double.parseDouble(tokens[1].trim());
                    int calories = Integer.parseInt(tokens[2].trim());
                    // create menuitem and add to list
                    MenuItem item = new MenuItem(name, price, calories);
                    menuItems.add(item);
                }
            }
            scanner.close(); // close scanner
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + filename);
        }
        return menuItems; // return the list
    }

    /**
     * prints the menu items in a formatted way
     * @param menuItems the list of menu items to print
     */
    public static void printMenu(ArrayList<MenuItem> menuItems) {
        // print header
        System.out.println("food truck menu");
        System.out.println("---------------");
        // loop through list and print each with a number
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i).toString());
        }
        System.out.println("---------------");
    }

    /**
     * prints the order receipt to the console and writes to a file
     * @param order the list of ordered menu items
     * @param customer the customer who ordered
     * @param today the date of the order
     */
    public static void printOrder(ArrayList<MenuItem> order, Customer customer, Date today) {
        // generate invoice number using provided method
        String invoice = getInvoiceNumber(customer, today);
        
        // calculate subtotal and total calories
        double subtotal = 0.0;
        int totalCalories = 0;
        for (MenuItem item : order) {
            subtotal += item.getPrice();
            totalCalories += item.getCalories();
        }
        // calculate tax at 6%
        double tax = subtotal * 0.06;
        double total = subtotal + tax;
        
        // build receipt string
        StringBuilder receipt = new StringBuilder();
        receipt.append("customer: " + customer.getName() + "\n");
        receipt.append("invoice number: " + invoice + "\n");
        receipt.append("date: " + today.toString() + "\n\n");
        receipt.append("ordered items:\n");
        receipt.append("-------------------------\n");
        for (MenuItem item : order) {
            receipt.append(item.toString() + "\n");
        }
        receipt.append("-------------------------\n");
        receipt.append("total calories: " + totalCalories + "\n");
        receipt.append("subtotal: $" + String.format("%.2f", subtotal) + "\n");
        receipt.append("6% tax: $" + String.format("%.2f", tax) + "\n");
        receipt.append("order total: $" + String.format("%.2f", total) + "\n");
        
        // print receipt to console
        System.out.println(receipt.toString());
        
        // write receipt to a file
        String fileName = "receipt_" + invoice + ".txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(receipt.toString());
            writer.close();
            System.out.println("receipt written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("error writing receipt to file");
        }
    }

    /**
     * creates an invoice number from the customer data and the date
     * @param customer the customer object
     * @param today the date object
     * @return the invoice number
     */
    public static String getInvoiceNumber(Customer customer, Date today) {
        /*
         * first two initials of the customer’s first name, converted to uppercase
         * first two initials of the customer’s last name, converted to uppercase
         * unicode value of the first character of the first name
         * unicode value of the first character of the last name
         * add them together and multiply by the length of the full name
         * concatenate this id to the initials described above
         * concatenate the day and month with no punctuation
         */
        String invoiceNumber = "";
        String[] nameParts = customer.getName().split(" ");
        String firstInitials = nameParts[0].substring(0, 2).toUpperCase();
        String lastInitials = "";
        if (nameParts.length > 1) {
            lastInitials = nameParts[1].substring(0, 2).toUpperCase();
        } else {
            lastInitials = "xx";
        }
        int firstUnicode = (int) nameParts[0].charAt(0);
        int lastUnicode = (nameParts.length > 1) ? (int) nameParts[1].charAt(0) : 0;
        int id = (firstUnicode + lastUnicode) * customer.getName().length();
        invoiceNumber = firstInitials + lastInitials + id;
        invoiceNumber += today.getDay() + "" + today.getMonth();
        return invoiceNumber;
    }
}
