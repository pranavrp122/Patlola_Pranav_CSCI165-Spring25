/**
 * this class represents an order item.
 * it holds a menuitem and a quantity.
 * no setters are provided; use the constructor to set values.
 */
public class OrderItem {

    // instance variables
    private MenuItem menuItem;
    private int quantity;

    /**
     * constructor to set a menuitem and quantity.
     * @param menuItem the menuitem chosen.
     * @param quantity the amount of the menuitem.
     */
    public OrderItem(MenuItem menuItem, int quantity) {
        // assume menuitem is not null and quantity is positive.
        // add simple validation if needed.
        if (menuItem == null) {
            throw new IllegalArgumentException("menuitem cannot be null");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("quantity must be at least 1");
        }
        // for privacy, create a copy of the menuitem
        this.menuItem = new MenuItem(menuItem);
        this.quantity = quantity;
    }

    /**
     * copy constructor for deep copy.
     * @param other the orderitem to copy.
     */
    public OrderItem(OrderItem other) {
        if (other == null) {
            throw new IllegalArgumentException("orderitem cannot be null");
        }
        this.menuItem = new MenuItem(other.menuItem);
        this.quantity = other.quantity;
    }

    /**
     * gets a copy of the menuitem.
     * @return a copy of the menuitem.
     */
    public MenuItem getMenuItem() {
        return new MenuItem(this.menuItem);
    }

    /**
     * gets the quantity.
     * @return the quantity.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * updates the quantity.
     * if the value is positive, it increases the quantity.
     * if the value is negative, the absolute value must not be greater than current quantity.
     * @param delta the amount to update the quantity by.
     */
    public void updateQuantity(int delta) {
        if (delta >= 0) {
            this.quantity += delta;
        } else {
            if (Math.abs(delta) <= this.quantity) {
                this.quantity += delta;
            }
            // if negative value is too large, quantity remains unchanged.
        }
    }

    /**
     * returns the string representation of this orderitem.
     * @return a string with the menuitem details and quantity.
     */
    public String toString() {
        return this.menuItem.toString() + " quantity: " + this.quantity;
    }

    /**
     * checks if two orderitems are equal.
     * uses menuitem.equals for deep comparison.
     * @param other the orderitem to compare.
     * @return true if both orderitems have equal menuitems and quantity.
     */
    public boolean equals(OrderItem other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        return this.quantity == other.quantity &&
               this.menuItem.equals(other.menuItem);
    }
}
