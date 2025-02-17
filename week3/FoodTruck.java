import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FoodTruck {
    public static void main(String[] args) {
        final int NUM_ITEMS = 3;
        String[] menu = new String[NUM_ITEMS];
        double[] prices = new double[NUM_ITEMS];
        int[] quantities = new int[NUM_ITEMS];

        // read menu data from menu.txt
        try (Scanner fileScanner = new Scanner(new File("menu.txt"))) {
            int index = 0;
            while (fileScanner.hasNextLine() && index < NUM_ITEMS) {
                // read the full item name even with any possible spaces
                menu[index] = fileScanner.nextLine().trim();
                if (fileScanner.hasNextLine()) {
                    String priceLine = fileScanner.nextLine().trim();
                    try {
                        prices[index] = Double.parseDouble(priceLine);
                    } catch (NumberFormatException e) {
                        System.out.println("invalid price format for " + menu[index]);
                        prices[index] = 0.0;
                    }
                }
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("menu.txt not found.");
            return;
        }

        // get customer name and order quantities
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Welcome to the CS food truck");
        System.out.print("Enter your name: ");
        String fullName = inputScanner.nextLine().trim();

        System.out.println("Enter the quantity of each item");
        System.out.println("===============================");
        for (int i = 0; i < NUM_ITEMS; i++) {
            // prompt in the format: "Chicken Shwarma Pita - $3.99: "
            System.out.printf("%s - $%.2f: ", menu[i], prices[i]);
            if (inputScanner.hasNextInt()) {
                quantities[i] = inputScanner.nextInt();
            } else {
                quantities[i] = 0;
                inputScanner.next();
            }
        }
        //  leftover newline
        inputScanner.nextLine();

        // compute totals
        double subtotal = 0.0;
        double[] itemTotals = new double[NUM_ITEMS];
        for (int i = 0; i < NUM_ITEMS; i++) {
            itemTotals[i] = quantities[i] * prices[i];
            subtotal += itemTotals[i];
        }
        double taxRate = 0.0625;
        double tax = subtotal * taxRate;
        double total = subtotal + tax;

        // get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter displayDateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter displayTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String displayDate = now.format(displayDateFormatter);
        String displayTime = now.format(displayTimeFormatter);

        // generate invoice number
        String invoiceNumber = generateInvoiceNumber(fullName, now);

        // build the receipt
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("Invoice Number: %s%n", invoiceNumber));
        receipt.append(String.format("Date: %s%n", displayDate));
        receipt.append(String.format("Time: %s%n", displayTime));
        receipt.append("\n");
        // header for the receipt
        receipt.append(String.format("%-30s%10s%10s%10s%n", "Item", "Quantity", "Price", "Total"));
        receipt.append("============================================================\n");
        for (int i = 0; i < NUM_ITEMS; i++) {
            receipt.append(String.format("%-30s%10d%10.2f%10.2f%n", 
                    menu[i], quantities[i], prices[i], itemTotals[i]));
        }
        receipt.append("============================================================\n");
        receipt.append(String.format("%-30s%30.2f%n", "Subtotal", subtotal));
        receipt.append(String.format("%-30s%30.2f%n", "6.25% Sales Tax", tax));
        receipt.append(String.format("%-30s%30.2f%n", "Total", total));

        // output the receipt to the console
        System.out.println();
        System.out.println(receipt.toString());

        // write the receipt to a file (invoiceNumber.txt)
        try (PrintWriter writer = new PrintWriter(invoiceNumber + ".txt")) {
            writer.print(receipt.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Error writing receipt to file.");
        }
    }

     // generates reciept
    private static String generateInvoiceNumber(String fullName, LocalDateTime dateTime) {
        String[] nameParts = fullName.split("\\s+");
        String firstName, lastName;
        if (nameParts.length >= 2) {
            firstName = nameParts[0];
            lastName = nameParts[nameParts.length - 1];
        } else {
            // if only one name is provided, use it for both first and last.
            firstName = fullName;
            lastName = fullName;
        }
        String firstInitials = firstName.length() >= 2 ? firstName.substring(0, 2).toUpperCase() 
                                                      : firstName.toUpperCase();
        String lastInitials = lastName.length() >= 2 ? lastName.substring(0, 2).toUpperCase() 
                                                    : lastName.toUpperCase();
        String initials = firstInitials + lastInitials;

        // calculate the unique number using the unicode values of the first letters and the length of fullName.
        int sum = firstName.charAt(0) + lastName.charAt(0);
        int product = sum * fullName.length();

        // format date and time as two-digit numbers.
        String day   = String.format("%02d", dateTime.getDayOfMonth());
        String month = String.format("%02d", dateTime.getMonthValue());
        String hour  = String.format("%02d", dateTime.getHour());
        String minute= String.format("%02d", dateTime.getMinute());

        return initials + product + day + month + hour + minute;
    }
}
