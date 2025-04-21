/*
	checkingaccount.java
	subclass of account with overdraft limit
*/

public class CheckingAccount extends Account {
    private double overdraftLimit;  // max negative balance allowed

    /**
     * constructor with balance and overdraft
     */
    public CheckingAccount(int accountNumber, Customer owner, Date created, double balance, double overdraftLimit) {
        super(accountNumber, owner, created, balance);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * constructor with manager and overdraft
     */
    public CheckingAccount(int accountNumber, Employee manager, Customer owner, Date created, double overdraftLimit) {
        super(accountNumber, manager, owner, created);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * copy constructor
     */
    public CheckingAccount(CheckingAccount toCopy) {
        super(toCopy);
        this.overdraftLimit = toCopy.overdraftLimit;
    }

    /**
     * withdraw with overdraft support
     */
    @Override
    public void withdraw(double sum) {
        if (sum > 0) {
            double newBalance = getBalance() - sum;
            if (newBalance >= -overdraftLimit) {
                super.withdraw(sum);
            } else {
                System.err.println("withdraw amount exceeds overdraft limit");
            }
        } else {
            System.err.println("Account.withdraw(...): cannot withdraw negative amount.");
        }
    }

    /**
     * check if account is in overdraft
     */
    public boolean isInOverDraft() {
        return getBalance() < 0;
    }

    @Override
    public void update() {
        if (isInOverDraft()) {
            System.out.println("account " + getAccountNumber() + " is in overdraft");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
               "\noverdraft limit: " + overdraftLimit;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))            return false;
        if (getClass() != obj.getClass())   return false;
        CheckingAccount other = (CheckingAccount) obj;
        return overdraftLimit == other.overdraftLimit;
    }
}
