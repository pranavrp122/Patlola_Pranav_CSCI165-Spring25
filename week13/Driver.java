// simple driver to demo custom exceptions

/**
 * driver app demonstrating exception handling scenarios
 */
public class Driver {
    /**
     * main method to run exception demos
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        /**
         * demo invalid customer id scenario
         */
        try {
            // instantiate customer with malformed id for IDNotWellFormedException
            Customer badCust = new Customer("joe", "smith", "1234567890",
                                            new Date(1, 1, 2000),
                                            new Date(5, 5, 2025),
                                            "X12");
        } catch (IDNotWellFormedException e) {
            // print the exception message for invalid id
            System.out.println(e.getMessage());
        }

        /**
         * demo invalid account number scenario
         */
        try {
            // create valid customer for account number test
            Customer c = new Customer("A", "B", "1234567890",
                                      new Date(1,1,2000),
                                      new Date(5,5,2025),
                                      "A123");
            // attempt to create account with wrong number length
            Account badAcct = new Account(123, c, new Date(5,5,2025), 0);
        } catch (Exception e) {
            // output exception message for invalid account number
            System.out.println(e.getMessage());
        }

        /**
         * demo deposit over max for checking scenario
         */
        try {
            // set up customer and checking account for deposit test
            Customer c2 = new Customer("A","B","1234567890",
                                       new Date(1,1,2000),
                                       new Date(5,5,2025),
                                       "B234");
            // instantiate checking account with overdraft limit
            CheckingAccount ca = new CheckingAccount(54321, c2, new Date(5,5,2025),
                                                     0, 500);
            // attempt deposit that should exceed max checking balance
            ca.deposit(2000000);
        } catch (Exception e) {
            // print exception message for deposit violation
            System.out.println(e.getMessage());
        }

        /**
         * demo too many withdrawals in savings scenario
         */
        try {
            // set up customer and savings account for withdrawal test
            Customer c3 = new Customer("C","D","1234567890",
                                       new Date(1,1,2000),
                                       new Date(5,5,2025),
                                       "C345");
            // instantiate savings account with initial balance and interest rate
            SavingsAccount sa = new SavingsAccount(67890, c3, new Date(5,5,2025),
                                                   1000, 0.02);
            // perform withdrawals to trigger overdraft exception
            sa.withdraw(10);
            sa.withdraw(10);
            sa.withdraw(10);
            sa.withdraw(10);
        } catch (Exception e) {
            // print exception message for too many withdrawals
            System.out.println(e.getMessage());
        }
    }
}
