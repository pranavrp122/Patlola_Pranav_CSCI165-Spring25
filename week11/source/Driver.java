/*
 * Driver class to test Account, SavingsAccount, CheckingAccount, and Bank.
 */

 public class Driver {
    public static void main(String[] args) {
        System.out.println("=== starting driver ===");

        // create some dates
        Date dob1      = new Date(1, 15, 1990);
        Date join1     = new Date(6, 1, 2020);
        Date hire1     = new Date(3, 1, 2018);
        Date acctDate1 = new Date(7, 10, 2021);
        Date acctDate2 = new Date(8, 5, 2022);

        // create customer and employee
        System.out.println("\n-- creating customer and employee --");
        Customer cust1 = new Customer("alice", "smith", "1234567890", dob1, join1, "CUST100");
        System.out.println("customer created:\n" + cust1);

        Employee emp1 = new Employee("bob", "jones");
        emp1.setPhoneNumber("0987654321");
        emp1.setDOB(new Date(2, 20, 1985));
        emp1.setHireDate(hire1);
        emp1.setId(9001);
        emp1.setDepartment("sales");
        System.out.println("\nemployee created:\n" + emp1);

        // create accounts
        System.out.println("\n-- creating accounts --");
        Account acct1 = new Account(1001, cust1, acctDate1, 500.0);
        System.out.println("\nbasic account:\n" + acct1);

        SavingsAccount sav1 = new SavingsAccount(2001, emp1, cust1, acctDate1, 1000.0, 0.05);
        System.out.println("\nsavings account:\n" + sav1);

        CheckingAccount chk1 = new CheckingAccount(3001, cust1, acctDate2, 200.0, 50.0);
        System.out.println("\nchecking account:\n" + chk1);

        // test deposit/withdraw
        System.out.println("\n-- testing deposit and withdraw --");
        System.out.println("depositing $200 into basic account");
        acct1.deposit(200.0);
        System.out.println("new balance: " + acct1.getBalance());

        System.out.println("\nwithdrawing $250 from checking account");
        chk1.withdraw(250.0);
        System.out.println("checking balance: " + chk1.getBalance());

        System.out.println("\nwithdrawing $100 more from checking account (should exceed overdraft)");
        chk1.withdraw(100.0);
        System.out.println("checking balance: " + chk1.getBalance());

        // test interest
        System.out.println("\n-- testing savings interest --");
        System.out.println("adding interest to savings account");
        sav1.update();
        System.out.println("savings balance: " + sav1.getBalance());

        // test transfer
        System.out.println("\n-- testing transfer between accounts --");
        System.out.println("transferring $100 from savings to basic account");
        sav1.transferTo(acct1, 100.0);
        System.out.println("basic balance: " + acct1.getBalance());
        System.out.println("savings balance: " + sav1.getBalance());

        // test bank operations
        System.out.println("\n-- testing bank operations --");
        Bank bank = new Bank();
        bank.openAccount(acct1);
        bank.openAccount(sav1);
        bank.openAccount(chk1);

        System.out.println("paying 1% dividend to all accounts");
        bank.payDividend(0.01);

        System.out.println("\napplying bank update (interest and overdraft notices)");
        bank.update();

        System.out.println("\nprinting all accounts in bank:");
        bank.printAccounts();

        System.out.println("\n=== driver finished ===");
    }
}
