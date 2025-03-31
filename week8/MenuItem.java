/**
 * menuitem class to hold menu item data
 * this class holds the name, price, and calories for a menu item
 * it uses a specific approach for domain validation to pass the provided tests:
 * - the constructor uses default values if invalid data is passed in
 * - the setters do nothing if invalid data is passed in, leaving the old value unchanged
 */
public class MenuItem implements Comparable<MenuItem> {

    // private instance variables with default values at the class level
    private String name;
    private double price = 1.0;  // initialized to 1.0
    private int calories = 0;    // initialized to 0

    /**
     * no argument constructor
     * uses default values for all fields
     */
    public MenuItem() {
        // name can be anything simple
        this.name = "item";
        // price and calories remain at their default class-level initialization
    }

    /**
     * overloaded constructor that takes name, price, and calories
     * if the price or calories are invalid, they remain at their default
     * @param name the name of the item
     * @param price the price of the item
     * @param calories the calories of the item
     */
    public MenuItem(String name, double price, int calories) {
        // set name (simple null check)
        if(name != null) {
            this.name = name;
        } else {
            this.name = "item";
        }
        // pass the values to the setter methods
        this.setPrice(price);
        this.setCalories(calories);
    }

    /**
     * gets the name of the menu item
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the name of the menu item
     * does a simple null check
     * @param name the name to set
     */
    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
    }

    /**
     * gets the price of the menu item
     * @return the price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * sets the price of the menu item
     * if the price is not greater than zero, do nothing
     * this preserves the old value, matching the test's expectation
     * note that the constructor's default helps if an invalid price is passed initially
     * @param price the price to set
     */
    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
        // else do nothing, leaving the old value unchanged
    }

    /**
     * gets the calories of the menu item
     * @return the calories
     */
    public int getCalories() {
        return this.calories;
    }

    /**
     * sets the calories of the menu item
     * if the calories is negative, do nothing
     * this preserves the old value, matching the test's expectation
     * note that the constructor's default helps if invalid calories are passed initially
     * @param calories the calories to set
     */
    public void setCalories(int calories) {
        if (calories >= 0) {
            this.calories = calories;
        }
        // else do nothing, leaving the old value unchanged
    }

    /**
     * returns a string representation of the menu item
     * @return a string with name, price, and calories
     */
    public String toString() {
        return "name: " + this.name + ", price: $" + this.price + ", calories: " + this.calories;
    }

    /**
     * checks equality with another menu item
     * @param other the other menuitem to compare
     * @return true if name, price, and calories are the same
     */
    public boolean equals(MenuItem other) {
        if (this == other) return true;
        if (other == null) return false;
        return this.name.equals(other.name) &&
               this.price == other.price &&
               this.calories == other.calories;
    }

    /**
     * compares this menu item with another based on calories
     * returns -1 if this has fewer calories, 0 if equal, and 1 if more calories
     * @param other the other menu item to compare
     * @return -1, 0, or 1 based on calorie comparison
     */
    public int compareTo(MenuItem other) {
        if (this.calories < other.calories)
            return -1;
        else if (this.calories > other.calories)
            return 1;
        else
            return 0;
    }
}
