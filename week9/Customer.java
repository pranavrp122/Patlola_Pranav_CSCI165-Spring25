/**
 * this class represents a customer.
 * it holds the first name and last name.
 */
public class Customer {

    // instance variables
    private String firstName;
    private String lastName;

    /**
     * constructor to set the first name and last name.
     * @param firstName the customer's first name.
     * @param lastName the customer's last name.
     */
    public Customer(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("first name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("last name cannot be null or empty");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * copy constructor - creates a new customer that is a copy of the provided customer.
     * @param other the customer to copy.
     */
    public Customer(Customer other) {
        if (other == null) {
            throw new IllegalArgumentException("customer cannot be null");
        }
        this.firstName = other.firstName;
        this.lastName = other.lastName;
    }

    /**
     * gets the first name.
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * gets the last name.
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * returns a string representation of the customer.
     * @return the customer as a string.
     */
    public String toString() {
        return "customer: " + firstName + " " + lastName;
    }

    /**
     * checks if two customers are equal.
     * @param other the customer to compare.
     * @return true if both customers have the same first and last names.
     */
    public boolean equals(Customer other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        return this.firstName.equals(other.firstName) &&
               this.lastName.equals(other.lastName);
    }
}
