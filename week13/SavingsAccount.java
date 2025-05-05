/*
    savingsaccount.java
    subclass of account with interest rate
*/

/**
 * savings account subclass of account that enforces minimum balance,
 * maximum withdrawals per month, and per-transaction limits,
 * plus applies interest on update.
 */
public class SavingsAccount extends Account {
    private double interestRate;  // interest rate (e.g. 0.02 for 2%)

    public static final double MIN_SAVINGS_BALANCE = 100.00;   // minimum allowed balance
    public static final int    MAX_WITHDRAWALS      = 3;      // max withdrawals per month
    private int               withdrawCount        = 0;      // tracks withdrawals this period
    public static final double MAX_WITHDRAW_AMOUNT  = 5000.00; // maximum per transaction

    /**
     * constructor with initial balance and interest rate
     * @param accountNumber five-digit account number
     * @param owner account owner
     * @param created date account was created
     * @param balance initial balance
     * @param interestRate interest rate to apply on update
     * @throws invalidaccountnumberexception if account number not 5 digits
     * @throws idnotwellformedexception if owner custID invalid
     */
    public SavingsAccount(int accountNumber, Customer owner, Date created,
                          double balance, double interestRate)
            throws InvalidAccountNumberException, IDNotWellFormedException {
        super(accountNumber, owner, created, balance); // initialize in Account
        this.interestRate = interestRate;               // set interest rate
    }

    /**
     * constructor with manager and interest rate (zero balance)
     * @param accountNumber five-digit account number
     * @param manager account manager
     * @param owner account owner
     * @param created date account was created
     * @param interestRate interest rate to apply on update
     * @throws invalidaccountnumberexception if account number not 5 digits
     * @throws idnotwellformedexception if owner custID invalid
     */
    public SavingsAccount(int accountNumber, Employee manager, Customer owner,
                          Date created, double interestRate)
            throws InvalidAccountNumberException, IDNotWellFormedException {
        super(accountNumber, manager, owner, created); // call parent constructor
        this.interestRate = interestRate;               // set interest rate
    }

    /**
     * constructor with manager, initial balance, and interest rate
     * @param accountNumber five-digit account number
     * @param manager account manager
     * @param owner account owner
     * @param created date account was created
     * @param balance initial balance
     * @param interestRate interest rate to apply on update
     * @throws invalidaccountnumberexception if account number not 5 digits
     * @throws idnotwellformedexception if owner custID invalid
     */
    public SavingsAccount(int accountNumber, Employee manager, Customer owner,
                          Date created, double balance, double interestRate)
            throws InvalidAccountNumberException, IDNotWellFormedException {
        super(accountNumber, owner, created, balance); // initialize in Account
        setManager(manager);                            // assign manager safely
        this.interestRate = interestRate;               // set interest rate
    }

    /**
     * copy constructor
     * @param toCopy existing SavingsAccount to copy
     * @throws idnotwellformedexception if owner custID invalid
     */
    public SavingsAccount(SavingsAccount toCopy)
            throws IDNotWellFormedException {
        super(toCopy);                 // copy Account fields
        this.interestRate = toCopy.interestRate; // copy interest rate
        this.withdrawCount = toCopy.withdrawCount; // copy withdrawal count
    }

    /**
     * withdraw funds enforcing count, per-transaction and minimum balance rules
     * @param sum amount to withdraw
     * @throws invalidbalanceexception if sum negative or resulting balance &lt; MIN_SAVINGS_BALANCE
     * @throws overdraftexception if too many withdrawals or sum &gt; MAX_WITHDRAW_AMOUNT
     */
    @Override
    public void withdraw(double sum) throws InvalidBalanceException, OverdraftException {
        // increment and check withdrawal count
        if (++withdrawCount > MAX_WITHDRAWALS) {
            throw new OverdraftException("too many withdrawals: " + withdrawCount);
        }
        // enforce per-transaction limit
        if (sum > MAX_WITHDRAW_AMOUNT) {
            throw new OverdraftException("withdraw exceeds limit: " + sum);
        }
        // disallow negative amounts
        if (sum < 0) {
            throw new InvalidBalanceException("withdraw negative: " + sum);
        }
        // enforce minimum balance
        if (getBalance() - sum < MIN_SAVINGS_BALANCE) {
            throw new InvalidBalanceException("below min balance: " + (getBalance() - sum));
        }
        super.withdraw(sum); // perform withdrawal
    }

    /**
     * apply interest to the balance by depositing computed interest
     * ignores any exception since interest is positive
     */
    public void addInterest() {
        double interest = getBalance() * interestRate; // compute interest
        try {
            deposit(interest); // add interest
        } catch (InvalidBalanceException e) {
            // should not occur (interest positive and within limits)
        }
    }

    /**
     * update account by applying interest
     */
    @Override
    public void update() {
        addInterest(); // apply interest on update
    }

    /**
     * string representation including interest rate
     * @return account info plus interest rate
     */
    @Override
    public String toString() {
        return super.toString() +
               "\ninterest rate: " + interestRate; // append interest rate
    }

    /**
     * equality check including interest rate
     * @param obj object to compare
     * @return true if all account fields and interestRate match
     */
    @Override
    public boolean equals(Object obj) {
        // first compare Account fields
        if (!super.equals(obj)) {
            return false;
        }
        // ensure same class
        if (getClass() != obj.getClass()) {
            return false;
        }
        SavingsAccount other = (SavingsAccount) obj;
        // compare interest rate
        return interestRate == other.interestRate;
    }
}