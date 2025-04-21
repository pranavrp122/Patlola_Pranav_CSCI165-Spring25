import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class CheckingAccountTest {

    // helper to build a sample customer
    private Customer makeCustomer() {
        Date dob    = new Date(2, 2, 2000);
        Date joined = new Date(3, 3, 2020);
        return new Customer("alice", "smith", "1234567890", dob, joined, "C100");
    }

    // helper to build a sample employee
    private Employee makeEmployee() {
        Employee e = new Employee("bob", "jones");
        e.setPhoneNumber("0987654321");
        e.setDOB(new Date(1, 1, 1985));
        e.setHireDate(new Date(4, 4, 2015));
        e.setId(9001);
        e.setDepartment("sales");
        return e;
    }

    @Test
    void copyConstructor_ownerBased() {
        Customer cust = makeCustomer();
        Date created  = new Date(8, 5, 2022);
        CheckingAccount orig = new CheckingAccount(3001, cust, created, 500.0, 100.0);
        CheckingAccount copy = new CheckingAccount(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);
    }

    @Test
    void copyConstructor_managerBased() {
        Customer cust = makeCustomer();
        Employee mgr  = makeEmployee();
        Date created   = new Date(8, 5, 2022);
        CheckingAccount orig = new CheckingAccount(3002, mgr, cust, created, 50.0);
        CheckingAccount copy = new CheckingAccount(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);
    }

    @Test
    void equals_reflexive_symmetric_null_and_type() {
        Customer cust = makeCustomer();
        Date created  = new Date(1, 1, 2022);
        CheckingAccount a = new CheckingAccount(3003, cust, created, 200.0, 25.0);
        // reflexive
        assertEquals(a, a);
        // null
        assertNotEquals(a, null);
        // wrong type
        assertNotEquals(a, "foo");
        // symmetric
        CheckingAccount b = new CheckingAccount(3003, cust, created, 200.0, 25.0);
        assertEquals(a, b);
        assertEquals(b, a);
    }
}
