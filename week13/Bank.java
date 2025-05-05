/*
	bank.java
	manages collection of accounts with polymorphic updates
*/

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;  // list of all accounts

    /**
     * create empty bank
     */
    public Bank() {
        accounts = new ArrayList<>();
    }

    /**
     * open new account
     */
    public void openAccount(Account acct) {
        accounts.add(acct);
    }

    /**
     * close existing account
     */
    public void closeAccount(Account acct) {
        accounts.remove(acct);
    }

    /**
     * pay dividend to all accounts by percentage
     */
    public void payDividend(double percent) 
            throws InvalidBalanceException {
        for (Account acct : accounts) {
            double amount = acct.getBalance() * percent;
            acct.deposit(amount);
        }
    }

    /**
     * update all accounts polymorphically
     */
    public void update() {
        for (Account acct : accounts) {
            acct.update();
        }
    }

    /**
     * print all accounts
     */
    public void printAccounts() {
        for (Account acct : accounts) {
            System.out.println(acct);
        }
    }
}
