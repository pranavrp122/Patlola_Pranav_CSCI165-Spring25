/*
	savingsaccount.java
	subclass of account with interest rate
*/

public class SavingsAccount extends Account {
    private double interestRate;  // interest rate (e.g. 0.02 for 2%)

    /**
     * constructor with balance and interest
     */
    public SavingsAccount(int accountNumber, Customer owner, Date created, double balance, double interestRate) {
        super(accountNumber, owner, created, balance);
        this.interestRate = interestRate;
    }

    /**
     * constructor with manager and interest
     */
    public SavingsAccount(int accountNumber, Employee manager, Customer owner, Date created, double interestRate) {
        super(accountNumber, manager, owner, created);
        this.interestRate = interestRate;
    }

    /**
     * constructor with manager, initial balance, and interest rate
     */
    public SavingsAccount(int accountNumber, Employee manager, Customer owner, Date created, double balance, double interestRate) {
        super(accountNumber, owner, created, balance);
        setManager(manager);           // privacy protection for manager
        this.interestRate = interestRate;
    }

    /**
     * copy constructor
     */
    public SavingsAccount(SavingsAccount toCopy) {
        super(toCopy);
        this.interestRate = toCopy.interestRate;
    }

    /**
     * add interest to balance
     */
    public void addInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
    }

    @Override
    public void update() {
        addInterest();
    }

    @Override
    public String toString() {
        return super.toString() +
               "\ninterest rate: " + interestRate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))            return false;
        if (getClass() != obj.getClass())   return false;
        SavingsAccount other = (SavingsAccount) obj;
        return interestRate == other.interestRate;
    }
}
