/*
    checkingaccount.java
    subclass of account with overdraft limit
*/

/**
 * checking account subclass of account that enforces overdraft limits and max balance
 */
public class CheckingAccount extends Account {
    private double overdraftLimit;  // max negative balance allowed
    public static final double MAX_CHECK_BALANCE = 1000000.00; // maximum allowed balance

    /**
     * constructor with initial balance and overdraft limit
     * @param accountNumber five-digit account number
     * @param owner account owner
     * @param created account creation date
     * @param balance initial balance
     * @param overdraftLimit overdraft limit
     * @throws invalidaccountnumberexception if account number is not 5 digits
     * @throws idnotwellformedexception if owner customer ID is invalid
     */
    public CheckingAccount(int accountNumber, Customer owner, Date created,
                           double balance, double overdraftLimit)
            throws InvalidAccountNumberException, IDNotWellFormedException {
        super(accountNumber, owner, created, balance); // call parent constructor
        this.overdraftLimit = overdraftLimit; // set overdraft limit
    }

    /**
     * constructor with manager and overdraft limit
     * @param accountNumber five-digit account number
     * @param manager account manager
     * @param owner account owner
     * @param created account creation date
     * @param overdraftLimit overdraft limit
     * @throws invalidaccountnumberexception if account number is not 5 digits
     * @throws idnotwellformedexception if owner customer ID is invalid
     */
    public CheckingAccount(int accountNumber, Employee manager, Customer owner,
                           Date created, double overdraftLimit)
            throws InvalidAccountNumberException, IDNotWellFormedException {
        super(accountNumber, manager, owner, created); // call parent constructor
        this.overdraftLimit = overdraftLimit; // set overdraft limit
    }

    /**
     * copy constructor
     * @param toCopy existing CheckingAccount to copy
     * @throws idnotwellformedexception if owner customer ID is invalid
     */
    public CheckingAccount(CheckingAccount toCopy)
            throws IDNotWellFormedException {
        super(toCopy); // call Account copy constructor
        this.overdraftLimit = toCopy.overdraftLimit; // copy overdraft limit
    }

    /**
     * withdraw funds with overdraft enforcement
     * @param sum amount to withdraw
     * @throws invalidbalanceexception if sum is negative
     * @throws overdraftexception if sum exceeds overdraft limit
     */
    @Override
    public void withdraw(double sum) throws InvalidBalanceException, OverdraftException {
        // check negative withdrawal
        if (sum < 0) {
            throw new InvalidBalanceException("withdraw negative: " + sum);
        }
        // check overdraft limit
        if (getBalance() - sum < -overdraftLimit) {
            throw new OverdraftException("exceeds overdraft: " + sum);
        }
        super.withdraw(sum); // perform withdrawal
    }

    /**
     * deposit funds with max balance enforcement
     * @param sum amount to deposit
     * @throws invalidbalanceexception if sum is negative or deposit exceeds max balance
     */
    @Override
    public void deposit(double sum) throws InvalidBalanceException {
        // check negative deposit
        if (sum < 0) {
            throw new InvalidBalanceException("deposit negative: " + sum);
        }
        // check max balance
        if (getBalance() + sum > MAX_CHECK_BALANCE) {
            throw new InvalidBalanceException("exceeds max balance: " + sum);
        }
        super.deposit(sum); // perform deposit
    }

    /**
     * check if account is in overdraft
     * @return true if balance is below zero
     */
    public boolean isInOverDraft() {
        return getBalance() < 0; // return overdraft status
    }

    /**
     * update account status, printing overdraft message if applicable
     */
    @Override
    public void update() {
        if (isInOverDraft()) {
            System.out.println("account " + getAccountNumber() + " is in overdraft"); // notify
        }
    }

    /**
     * string representation including overdraft limit
     * @return account details and overdraft limit
     */
    @Override
    public String toString() {
        return super.toString() +
               "\noverdraft limit: " + overdraftLimit; // append overdraft limit
    }

    /**
     * equality check including overdraft limit
     * @param obj object to compare
     * @return true if this and obj have same fields
     */
    @Override
    public boolean equals(Object obj) {
        // use super.equals to compare Account fields
        if (!super.equals(obj)) {
            return false; // account fields differ
        }
        // ensure same class
        if (getClass() != obj.getClass()) {
            return false; // not same type
        }
        CheckingAccount other = (CheckingAccount) obj;
        // compare overdraft limit
        return overdraftLimit == other.overdraftLimit;
    }
}
