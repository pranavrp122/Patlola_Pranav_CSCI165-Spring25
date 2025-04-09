import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;

/**
 * this class represents an order.
 * it holds the date, invoiceid, customer and a list of orderitems.
 */
public class Order implements Comparable<Order> {

    private Date date;
    private String invoiceID;
    private Customer customer;
    private ArrayList<OrderItem> cart;

    /**
     * constructor that sets the customer.
     * it calls setdate and createinvoiceid.
     * @param customer the customer who made the order.
     */
    public Order(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }
        // use copy constructor for privacy
        this.customer = new Customer(customer);
        setDate();
        this.invoiceID = createInvoiceID();
        this.cart = new ArrayList<>();
    }

    /**
     * copy constructor for deep copy.
     * @param other the order to copy.
     */
    public Order(Order other) {
        if (other == null) {
            throw new IllegalArgumentException("order cannot be null");
        }
        this.date = new Date(other.date);
        this.invoiceID = other.invoiceID;
        this.customer = new Customer(other.customer);
        // deep copy of the cart
        this.cart = new ArrayList<>();
        for (OrderItem item : other.cart) {
            this.cart.add(new OrderItem(item));
        }
    }

    /**
     * returns a deep copy of the date.
     * @return a date copy.
     */
    public Date getDate() {
        return new Date(this.date);
    }

    /**
     * returns the invoiceid.
     * @return the invoiceid string.
     */
    public String getInvoiceID() {
        return this.invoiceID;
    }

    /**
     * returns a deep copy of the customer.
     * @return a customer copy.
     */
    public Customer getCustomer() {
        return new Customer(this.customer);
    }

    /**
     * returns a deep copy of the cart.
     * note: arraylist's clone method only does a shallow copy,
     * so we iterate and clone each orderitem.
     * @return a new arraylist with cloned orderitems.
     */
    public ArrayList<OrderItem> getCart() {
        ArrayList<OrderItem> copy = new ArrayList<>();
        for (OrderItem item : this.cart) {
            copy.add(new OrderItem(item));
        }
        return copy;
    }

    /**
     * adds one menuitem to the cart.
     * if the menuitem exists, increases its quantity.
     * @param menuItem the menuitem to add.
     */
    public void addItem(MenuItem menuItem) {
        addItem(menuItem, 1);
    }

    /**
     * adds a menuitem with a given quantity to the cart.
     * if the menuitem exists, increases its quantity.
     * @param menuItem the menuitem to add.
     * @param quantity the number of items to add.
     */
    public void addItem(MenuItem menuItem, int quantity) {
        if (menuItem == null || quantity < 1) {
            throw new IllegalArgumentException("invalid menuitem or quantity");
        }
        // check if item is already in the cart using equals (assumes menuitem has proper equals)
        for (OrderItem item : this.cart) {
            if (item.getMenuItem().equals(menuItem)) {
                item.updateQuantity(quantity);
                return;
            }
        }
        this.cart.add(new OrderItem(menuItem, quantity));
    }

    /**
     * removes an orderitem with the given menuitem.
     * @param menuItem the menuitem to remove.
     * @return the removed orderitem or null if not found.
     */
    public OrderItem removeItem(MenuItem menuItem) {
        for (int i = 0; i < this.cart.size(); i++) {
            OrderItem item = this.cart.get(i);
            if (item.getMenuItem().equals(menuItem)) {
                this.cart.remove(i);
                return item;
            }
        }
        return null;
    }

    /**
     * calculates the total cost of the order.
     * assumes menuitem has getPrice.
     * @return the total cost.
     */
    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : this.cart) {
            // assume menuitem.getPrice returns a double
            total += item.getMenuItem().getPrice() * item.getQuantity();
        }
        return total;
    }

    /**
     * calculates the tax for the given total.
     * using a fixed tax rate of 6%.
     * @param total the cost before tax.
     * @return the tax amount.
     */
    public double calculateTax(double total) {
        double taxRate = 0.06;
        return total * taxRate;
    }

    /**
     * writes the order details to a file.
     * file name is invoiceid + ".txt".
     */
    public void writeToFile() {
        String filename = this.invoiceID + ".txt";
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(this.toString());
        } catch (IOException e) {
            System.out.println("error writing file: " + e.getMessage());
        }
    }

    // private helper methods

    /**
     * sets the date using the current date.
     * uses localdatetime to get the current day, month and year.
     */
    private void setDate() {
        LocalDateTime now = LocalDateTime.now();
        // create a new date object with the current date
        this.date = new Date(now.getMonthValue(), now.getDayOfMonth(), now.getYear());
    }

    /**
     * creates the invoiceid.
     * rule: first two letters of first name and last name (uppercase),
     * then sum of unicode values of first letters multiplied by the length of the full name (without spaces),
     * then day, month and year with no punctuation.
     * @return the invoiceid as a string.
     */
    private String createInvoiceID() {
        String firstName = this.customer.getFirstName();
        String lastName = this.customer.getLastName();
        if(firstName == null || lastName == null || firstName.length() < 1 || lastName.length() < 1) {
            throw new IllegalArgumentException("customer names not valid");
        }
        // get initials
        String initials = (firstName.length() >= 2 ? firstName.substring(0, 2) : firstName).toUpperCase() +
                          (lastName.length() >= 2 ? lastName.substring(0, 2) : lastName).toUpperCase();
        // get unicode values of first characters and add them
        int code = firstName.charAt(0) + lastName.charAt(0);
        // get length of full name without spaces
        String fullName = firstName + lastName;
        int nameLength = fullName.length();
        int calculatedNumber = code * nameLength;
        // get day, month and year from date
        String datePart = "" + this.date.getDay() + this.date.getMonth() + this.date.getYear();
        return initials + calculatedNumber + datePart;
    }

    /**
     * returns a string that represents the order.
     * includes customer, date, each orderitem, total, tax and final amount.
     * @return the formatted order string.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("invoice id: ").append(this.invoiceID).append("\n");
        sb.append("customer: ").append(this.customer.toString()).append("\n");
        sb.append("date: ").append(this.date.toString()).append("\n");
        sb.append("order items:\n");
        for (OrderItem item : this.cart) {
            sb.append(item.toString()).append("\n");
        }
        double total = calculateTotal();
        double tax = calculateTax(total);
        sb.append("subtotal: ").append(total).append("\n");
        sb.append("tax: ").append(tax).append("\n");
        sb.append("total: ").append(total + tax).append("\n");
        return sb.toString();
    }

    /**
     * checks if two orders are equal.
     * compares customer, date, invoiceid and cart.
     * @param other the order to compare.
     * @return true if the orders are equal, false otherwise.
     */
    public boolean equals(Order other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (!this.invoiceID.equals(other.invoiceID))
            return false;
        if (!this.date.equals(other.date))
            return false;
        if (!this.customer.equals(other.customer))
            return false;
        // compare orderitems in cart (order and content must be same)
        if (this.cart.size() != other.cart.size())
            return false;
        for (int i = 0; i < this.cart.size(); i++) {
            if (!this.cart.get(i).equals(other.cart.get(i)))
                return false;
        }
        return true;
    }

    /**
     * compares two orders by date.
     * uses date.compareTo for ordering.
     * @param other the other order.
     * @return negative if this order is earlier, zero if equal, positive if later.
     */
    public int compareTo(Order other) {
        return this.date.compareTo(other.date);
    }
}
