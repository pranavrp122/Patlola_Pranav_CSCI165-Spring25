/*
    customer.java
    subclass of person capturing when customer joined and id
*/

public class Customer extends Person {
    private Date dateJoined;  // date customer joined
    private String custID;    // unique customer id

    /**
     * full constructor
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param DOB
     * @param dateJoined
     * @param custID
     */
    public Customer(String firstName, String lastName, String phoneNumber, Date DOB,
                    Date dateJoined, String custID) {
        super(firstName, lastName, phoneNumber, DOB);
        this.dateJoined = new Date(dateJoined); // privacy protection
        this.custID     = custID;
    }

    /**
     * constructor from person, dateJoined, and id
     * @param p
     * @param dateJoined
     * @param custID
     */
    public Customer(Person p, Date dateJoined, String custID) {
        super(p);
        this.dateJoined = new Date(dateJoined); // privacy protection
        this.custID     = custID;
    }

    /**
     * copy constructor
     * @param toCopy
     */
    public Customer(Customer toCopy) {
        super(toCopy);
        this.dateJoined = new Date(toCopy.dateJoined); // privacy protection
        this.custID     = toCopy.custID;
    }

    /**
     * get date joined
     * @return dateJoined
     */
    public Date getDateJoined() {
        return new Date(dateJoined); // privacy protection
    }

    /**
     * set date joined
     * @param dateJoined
     */
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = new Date(dateJoined); // privacy protection
    }

    /**
     * get customer id
     * @return custID
     */
    public String getCustID() {
        return custID;
    }

    /**
     * set customer id
     * @param custID
     */
    public void setCustID(String custID) {
        this.custID = custID;
    }

    @Override
    public String toString() {
        return super.toString() +
               "\njoined: " + dateJoined +
               "\nid: "     + custID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)                    return true;    // identity check
        if (obj == null)                    return false;   // null check
        if (getClass() != obj.getClass())   return false;   // origin check
        if (!super.equals(obj))             return false;   // compare person fields

        Customer other = (Customer) obj;    // downcast

        // compare dateJoined
        if (dateJoined == null) {
            if (other.dateJoined != null)   return false;
        } else if (!dateJoined.equals(other.dateJoined)) return false;

        // Note: custID is intentionally not compared as per test requirements
        return true;
    }
}