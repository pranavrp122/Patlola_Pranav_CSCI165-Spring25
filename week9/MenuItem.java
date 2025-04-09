/**
 * this class represents a menu item.
 * it holds a name, price and calories.
 */
public class MenuItem {

    // instance variables
    private String name;
    private double price;
    private int calories;

    /**
     * constructor to set the name, price and calories.
     * @param name the name of the menu item.
     * @param price the price of the menu item.
     * @param calories the calories of the menu item.
     */
    public MenuItem(String name, double price, int calories) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if(price < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        if(calories < 0) {
            throw new IllegalArgumentException("calories cannot be negative");
        }
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    /**
     * copy constructor to make a new copy of the menu item.
     * @param other the menuitem to copy.
     */
    public MenuItem(MenuItem other) {
        if(other == null) {
            throw new IllegalArgumentException("menuitem cannot be null");
        }
        this.name = other.name;
        this.price = other.price;
        this.calories = other.calories;
    }

    /**
     * returns the price.
     * @return the price as a double.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * returns the name.
     * @return the name as a string.
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns a string representation of the menu item.
     * @return a formatted string with the menu item details.
     */
    public String toString() {
        return "item: " + this.name + " price: " + this.price + " calories: " + this.calories;
    }

    /**
     * checks if two menuitems are equal.
     * @param other the menuitem to compare.
     * @return true if both menuitems have the same name, price and calories.
     */
    public boolean equals(MenuItem other) {
        if(this == other)
            return true;
        if(other == null)
            return false;
        return this.name.equals(other.name) &&
               Double.compare(this.price, other.price) == 0 &&
               this.calories == other.calories;
    }
}
