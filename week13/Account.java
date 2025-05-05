/*
	File:	Account.java
	Author:	Ken Whitener
	Date:	4/15/2024

 	A class for bank accounts.
	This class provides the basic functionality of accounts.
	It allows deposits and withdrawals but not overdraft limits or interest rates.
*/

public class Account {
    private Customer owner;            // the owner of the account
    private Employee accountManager;   // the manager of the account
    private Date dateCreated;          // date the account was created
    private int accountNumber;         // the account number
    private double balance;            // the current balance

    /**
     * constructor may throw if account number not 5 digits or cust id invalid
     * initializes account fields
     * @param accountNumber
     * @param owner
     * @param created
     * @param balance
     * @throws invalidaccountnumberexception if acct number malformed
     * @throws idnotwellformedexception if owner custID invalid
     */
    public Account(int accountNumber, Customer owner, Date created, double balance)
            throws InvalidAccountNumberException, IDNotWellFormedException {
        // enforce 5-digit acct number
        if (String.valueOf(accountNumber).length() != 5)
            throw new InvalidAccountNumberException("invalid acct #: " + accountNumber);
        this.accountNumber = accountNumber;
        this.owner = new Customer(owner);      // privacy protection (may throw)
        this.dateCreated = new Date(created);  // privacy protection
        this.balance = balance;
    }

    /**
     * constructor may throw if account number not 5 digits or cust id invalid
     * constructs account with zero initial balance
     * @param accountNumber
     * @param manager
     * @param owner
     * @param created
     * @throws invalidaccountnumberexception if acct number malformed
     * @throws idnotwellformedexception if owner custID invalid
     */
    public Account(int accountNumber, Employee manager, Customer owner, Date created)
            throws InvalidAccountNumberException, IDNotWellFormedException {
        this(accountNumber, owner, created, 0);          // call to overloaded constructor
        this.accountManager = new Employee(manager);     // privacy protection
    }

    /**
     * copy constructor
     * copies all account fields
     * @param toCopy
     * @throws idnotwellformedexception if toCopy.owner custID invalid
     */
    public Account(Account toCopy) throws IDNotWellFormedException {
        this.accountNumber = toCopy.accountNumber;
        this.owner = new Customer(toCopy.owner);         // privacy protection (may throw)
        this.dateCreated = new Date(toCopy.dateCreated); // privacy protection
        this.balance = toCopy.balance;
        if (toCopy.accountManager != null) {
            this.accountManager = new Employee(toCopy.accountManager); // privacy protection
        }
    }

    /**
     * deposit may throw if negative amount
     * deposits funds into the account
     * @param sum
     * @throws invalidbalanceexception if sum negative
     */
    public void deposit(double sum) throws InvalidBalanceException {
        // check negative deposit
        if (sum < 0)
            throw new InvalidBalanceException("deposit negative: " + sum);
        this.balance += sum;
    }

    /**
     * withdraw may throw if negative amount or overdraft (subclasses)
     * withdraws funds from the account
     * @param sum
     * @throws invalidbalanceexception if sum negative
     * @throws overdraftexception declared for subclasses
     */
    public void withdraw(double sum) throws InvalidBalanceException, OverdraftException {
        // check negative withdraw
        if (sum < 0)
            throw new InvalidBalanceException("withdraw negative: " + sum);
        this.balance -= sum;
    }

    /**
     * transfer sum to another account if same owner
     * @param otherAccount
     * @param amount
     * @throws invalidbalanceexception if deposit fails
     */
    public void transferTo(Account otherAccount, double amount)
            throws InvalidBalanceException {
        if (this.owner.equals(otherAccount.owner) && amount < this.balance)
            otherAccount.deposit(amount);
    }

    /**
     * update account; default does nothing
     * default no operation implementation
     */
    public void update() {
        // default implementation: no action
    }

    /**
     * @return the current balance
     * returns current account balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * @return the account number
     * returns identifier of the account
     */
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * @param manager
     * assigns a new account manager
     */
    public void setManager(Employee manager) {
        this.accountManager = new Employee(manager);   // privacy protection
    }

    /**
     * @return The account manager for this account
     * returns copy of account manager
     */
    public Employee getManager() {
        return new Employee(this.accountManager);      // privacy protection
    }

    /**
     * @return The date the account was created
     * returns copy of creation date
     */
    public Date getDateCreated() {
        return new Date(dateCreated);                  // privacy protection
    }

    /**
     * @return The account owner
     * returns copy of the account owner
     */
    public Customer getOwner()
            throws IDNotWellFormedException {
        return new Customer(this.owner);               // privacy protection
    }

    /**
     * check equality based on all fields
     * @param obj object to compare
     * @return true if all fields match
     */
    @Override
    public boolean equals(Object obj) {
        // identity check
        if (this == obj) return true;
        // null check
        if (obj == null) return false;
        // class check
        if (getClass() != obj.getClass()) return false;

        Account other = (Account) obj; // safe downcast after checks

        // compare accountManager
        if (accountManager == null) {
            if (other.accountManager != null) return false;
        } else if (!accountManager.equals(other.accountManager)) {
            return false;
        }

        // compare accountNumber
        if (accountNumber != other.accountNumber) return false;

        // compare balance
        if (balance != other.balance) return false;

        // compare owner
        if (owner == null) {
            if (other.owner != null) return false;
        } else if (!owner.equals(other.owner)) return false;

        return true; // all fields match
    }

}
