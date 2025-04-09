import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this is the driver class for the food truck application.
 * it reads the menu from products.txt, takes user order inputs, and creates an order.
 */
public class FoodTruckDriver {
    public static void main(String[] args) {
        // read menu from products.txt into an arraylist of menuitem objects
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("products.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                // expected format: name,price,calories
                String[] tokens = line.split(",");
                if (tokens.length != 3) {
                    System.out.println("invalid product format: " + line);
                    continue;
                }
                String itemName = tokens[0].trim();
                double price = 0.0;
                int calories = 0;
                try {
                    price = Double.parseDouble(tokens[1].trim());
                    calories = Integer.parseInt(tokens[2].trim());
                } catch(NumberFormatException e) {
                    System.out.println("invalid number format for item: " + itemName);
                    continue;
                }
                menuItems.add(new MenuItem(itemName, price, calories));
            }
        } catch (FileNotFoundException e) {
            System.out.println("products.txt not found.");
            return;
        }
        
        if (menuItems.isEmpty()) {
            System.out.println("no products loaded.");
            return;
        }
        
        // get customer info and create order
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("welcome to the cs food truck");
        System.out.print("enter customer's first name: ");
        String firstName = inputScanner.nextLine().trim();
        System.out.print("enter customer's last name: ");
        String lastName = inputScanner.nextLine().trim();
        
        Customer customer = new Customer(firstName, lastName);
        Order order = new Order(customer);
        
        boolean addingItems = true;
        while (addingItems) {
            // display menu items with numbering
            System.out.println("\navailable menu items:");
            for (int i = 0; i < menuItems.size(); i++) {
                // use toString from menuitem to show details
                System.out.println((i + 1) + ". " + menuItems.get(i).toString());
            }
            
            System.out.print("\nenter the number of the item you want to order: ");
            int choice = -1;
            if (inputScanner.hasNextInt()) {
                choice = inputScanner.nextInt();
            } else {
                System.out.println("invalid input");
                inputScanner.next(); // consume invalid token
                continue;
            }
            
            if (choice < 1 || choice > menuItems.size()) {
                System.out.println("invalid choice");
                continue;
            }
            MenuItem selectedItem = menuItems.get(choice - 1);
            
            System.out.print("enter quantity for the selected item: ");
            int quantity = 0;
            if (inputScanner.hasNextInt()) {
                quantity = inputScanner.nextInt();
            } else {
                System.out.println("invalid input for quantity");
                inputScanner.next(); // consume invalid token
                continue;
            }
            
            order.addItem(selectedItem, quantity);
            
            System.out.print("do you want to add another item? (yes/no): ");
            inputScanner.nextLine(); // consume leftover newline
            String response = inputScanner.nextLine().trim();
            if (response.equalsIgnoreCase("no")) {
                addingItems = false;
            }
        }
        
        // display order details and write receipt to file
        System.out.println("\norder details:");
        System.out.println(order.toString());
        order.writeToFile();
        
        inputScanner.close();
    }
}
