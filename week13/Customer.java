/*
    customer.java
    subclass of person capturing when customer joined and id
*/

/**
 * customer subclass of person that adds join date and id enforcement
 */
public class Customer extends Person {
    private Date dateJoined;  // date customer joined
    private String custID;    // unique customer id

    /**
     * full constructor
     * @param firstName customer's first name
     * @param lastName customer's last name
     * @param phoneNumber customer's phone number
     * @param DOB customer's date of birth
     * @param dateJoined date the customer joined
     * @param custID unique customer id
     * @throws idnotwellformedexception if id invalid (letter + 3 digits)
     */
    public Customer(String firstName, String lastName, String phoneNumber, Date DOB,
                    Date dateJoined, String custID) throws IDNotWellFormedException {
        super(firstName, lastName, phoneNumber, DOB); // initialize person fields
        // check cust id format: letter then 3 digits
        if (!custID.matches("[A-Za-z]\\d{3}")) {
            throw new IDNotWellFormedException("invalid custID: " + custID);
        }
        this.dateJoined = new Date(dateJoined); // copy date for privacy protection
        this.custID = custID; // set validated id
    }

    /**
     * constructor from existing person, join date, and id
     * @param p existing person to copy
     * @param dateJoined date the customer joined
     * @param custID unique customer id
     * @throws idnotwellformedexception if id invalid (letter + 3 digits)
     */
    public Customer(Person p, Date dateJoined, String custID) throws IDNotWellFormedException {
        super(p); // copy person fields
        // check cust id format: letter then 3 digits
        if (!custID.matches("[A-Za-z]\\d{3}")) {
            throw new IDNotWellFormedException("invalid custID: " + custID);
        }
        this.dateJoined = new Date(dateJoined); // copy date for privacy protection
        this.custID = custID; // set validated id
    }

    /**
     * copy constructor
     * @param toCopy existing customer to copy
     * @throws idnotwellformedexception if copied id invalid
     */
    public Customer(Customer toCopy) throws IDNotWellFormedException {
        super(toCopy); // copy person fields
        // check cust id format: letter then 3 digits
        if (!toCopy.custID.matches("[A-Za-z]\\d{3}")) {
            throw new IDNotWellFormedException("invalid custID: " + toCopy.custID);
        }
        this.dateJoined = new Date(toCopy.dateJoined); // copy date for privacy protection
        this.custID = toCopy.custID; // copy validated id
    }

    /**
     * get the date customer joined
     * @return a copy of dateJoined
     */
    public Date getDateJoined() {
        return new Date(dateJoined); // return copy for privacy protection
    }

    /**
     * set the date customer joined
     * @param dateJoined new join date
     */
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = new Date(dateJoined); // copy date for privacy protection
    }

    /**
     * get the customer id
     * @return the customer id (letter + 3 digits)
     */
    public String getCustID() {
        return custID;
    }

    /**
     * set the customer id
     * @param custID new customer id
     * @throws idnotwellformedexception if id invalid (letter + 3 digits)
     */
    public void setCustID(String custID) throws IDNotWellFormedException {
        // check cust id format: letter then 3 digits
        if (!custID.matches("[A-Za-z]\\d{3}")) {
            throw new IDNotWellFormedException("invalid custID: " + custID);
        }
        this.custID = custID; // set validated id
    }

    /**
     * string representation including join date and id
     * @return string with person info, joined date, and id
     */
    @Override
    public String toString() {
        return super.toString() +
               "\njoined: " + dateJoined + // show join date
               "\nid: " + custID;          // show customer id
    }

    /**
     * equality check comparing all fields except archived fields
     * @param obj object to compare
     * @return true if this and obj are equal
     */
    @Override
    public boolean equals(Object obj) {
        // identity check
        if (this == obj) return true;
        // null check
        if (obj == null) return false;
        // type check
        if (getClass() != obj.getClass()) return false;
        // compare person fields via super
        if (!super.equals(obj)) return false;

        Customer other = (Customer) obj; // safe cast after class check

        // compare dateJoined
        if (dateJoined == null) {
            if (other.dateJoined != null) return false;
        } else if (!dateJoined.equals(other.dateJoined)) return false;

        // custID intentionally not compared per requirements
        return true;
    }
}
