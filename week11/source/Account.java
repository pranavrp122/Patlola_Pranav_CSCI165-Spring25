/*
	File:	Account.java
	Author:	Ken Whitener
	Date:	4/15/2024

 	A class for bank accounts.
	This class provides the basic functionality of accounts.
	It allows deposits and withdrawals but not overdraft limits or interest rates.
*/

public class Account {
    private Customer	owner;			// the owner of the account
    private Employee	accountManager;	// the manager of the account
    private Date		dateCreated; 	// date the account was created
    private int 		accountNumber;	// the account number
    private double 		balance;  		// the current balance
    
    /**
     * 
     * @param accountNumber
     * @param owner
     * @param created
     * @param balance
     */
    public Account(int accountNumber, Customer owner, Date created, double balance) {
        this.accountNumber   = accountNumber;
        this.owner           = new Customer(owner);      // privacy protection
        this.dateCreated     = new Date(created);        // privacy protection
        this.balance         = balance;
    }

    /**
     * 
     * @param accountNumber
     * @param manager
     * @param owner
     * @param created
     */
    public Account(int accountNumber, Employee manager, Customer owner, Date created) {
        this(accountNumber, owner, created, 0);          // call to overloaded constructor
        this.accountManager = new Employee(manager);     // privacy protection
    }

    /**
     * copy constructor
     * @param toCopy
     */
    public Account(Account toCopy) {
        this.accountNumber   = toCopy.accountNumber;
        this.owner           = new Customer(toCopy.owner);       // privacy protection
        this.dateCreated     = new Date(toCopy.dateCreated);     // privacy protection
        this.balance         = toCopy.balance;
        if (toCopy.accountManager != null) {
            this.accountManager = new Employee(toCopy.accountManager); // privacy protection
        }
    }

    /**
     * deposit sum into account
     * @param sum
     */
    public void deposit(double sum) {
        if (sum > 0)    this.balance += sum;
        else            System.err.println("Account.deposit(...): cannot deposit negative amount.");
    }

    /**
     * withdraw sum from account
     * @param sum
     */
    public void withdraw(double sum) {
        if (sum > 0)    this.balance -= sum;
        else            System.err.println("Account.withdraw(...): cannot withdraw negative amount.");
    }

    public void transferTo(Account otherAccount, double amount) {
        if (this.owner.equals(otherAccount.owner) && amount < this.balance)
            otherAccount.deposit(amount);
    }

    /**
     * update account; default does nothing
     */
    public void update() {
        // default implementation: no action
    }

    /**
     * @return the current balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * @return the account number
     */
    public double getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * @param manager
     */
    public void setManager(Employee manager) {
        this.accountManager = new Employee(manager);   // privacy protection
    }

    /**
     * @return The account manager for this account
     */
    public Employee getManager() {
        return new Employee(this.accountManager);      // privacy protection
    }

    /**
     * @return The date the account was created
     */
    public Date getDateCreated() {
        return new Date(dateCreated);                  // privacy protection
    }

    /**
     * @return The account owner
     */
    public Customer getOwner() {
        return new Customer(this.owner);               // privacy protection
    }

    @Override
    public String toString() {
        return  "Account Number: "   + accountNumber   +
                "\nOpened: "         + dateCreated     +
                "\nOwner: "          + owner           +
                "\nManager: "        + accountManager  +
                "\nBalance: "        + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)                    return true;    // identity check
        if (obj == null)                    return false;   // null check
        if (getClass() != obj.getClass())   return false;   // origin check

        Account other = (Account) obj;

        if (accountManager == null) {
            if (other.accountManager != null)
                return false;
        } else if (!accountManager.equals(other.accountManager))
            return false;
        if (accountNumber != other.accountNumber)
            return false;
        if (balance != other.balance)
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        return true;
    }
}
