import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * customer tests class to test customer methods and validations
 * tests include set methods (email and phone), equals, and compareto methods
 */
public class CustomerTests {
    
    // declare test instances
    Customer customer1;
    Customer customer2;

    /**
     * setup method runs before each test
     */
    @BeforeEach
    public void setup() {
        // initialize customer with valid data
        customer1 = new Customer("jim lahey", "jim@lahey.com", "1234567890");
        customer2 = new Customer("john doe", "john@doe.com", "0987654321");
    }

    /**
     * test setname method
     */
    @Test
    public void testSetName() {
        customer1.setName("new name");
        assertEquals("new name", customer1.getName(), "name should be updated to new name");
        
        // setting null should not change the name
        customer1.setName(null);
        assertEquals("new name", customer1.getName(), "name should remain unchanged if null is passed");
    }

    /**
     * test setemail method with valid input
     * note: expected behavior is that a valid email updates the field.
     * if the underlying logic fails to update because of semantic issues, document it here.
     */
    @Test
    public void testSetEmailValid() {
        String validEmail = "jane.doe@gmail.com";
        customer1.setEmail(validEmail);
        // expected: email is updated if valid; if not, note the semantic error in the implementation.
        assertEquals(validEmail, customer1.getEmail(), "email should be updated to the valid email address");
    }

    /**
     * test setemail method with invalid inputs
     */
    @Test
    public void testSetEmailInvalid() {
        // test with null input; should not change the email
        customer1.setEmail(null);
        assertEquals("jim@lahey.com", customer1.getEmail(), "email should remain unchanged on null input");

        // test with missing '@'
        customer1.setEmail("janedoegmail.com");
        assertEquals("jim@lahey.com", customer1.getEmail(), "email should remain unchanged if '@' is missing");

        // test with prefix too long (more than 64 characters)
        String longPrefix = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; // 65 a's
        customer1.setEmail(longPrefix + "@gmail.com");
        assertEquals("jim@lahey.com", customer1.getEmail(), "email should remain unchanged if prefix is too long");

        // test with consecutive dots in prefix
        customer1.setEmail("jane..doe@gmail.com");
        assertEquals("jim@lahey.com", customer1.getEmail(), "email should remain unchanged if there are consecutive dots");

        // test with dot at beginning of prefix
        customer1.setEmail(".janedoe@gmail.com");
        assertEquals("jim@lahey.com", customer1.getEmail(), "email should remain unchanged if dot is at the beginning");

        // test with dot at end of prefix
        customer1.setEmail("janedoe.@gmail.com");
        assertEquals("jim@lahey.com", customer1.getEmail(), "email should remain unchanged if dot is at the end");
    }

    /**
     * test setphone method with valid input
     */
    @Test
    public void testSetPhoneValid() {
        customer1.setPhone("1112223333");
        assertEquals("1112223333", customer1.getPhone(), "phone should be updated with valid 10-digit number");
    }

    /**
     * test setphone method with invalid input
     */
    @Test
    public void testSetPhoneInvalid() {
        // phone number too short; should not update the phone
        customer1.setPhone("12345");
        assertEquals("1234567890", customer1.getPhone(), "phone should remain unchanged if input length is not 10");

        // phone number containing non-digit characters
        customer1.setPhone("12345abcde");
        assertEquals("1234567890", customer1.getPhone(), "phone should remain unchanged if input contains non-digits");
    }

    /**
     * test equals method for customers
     */
    @Test
    public void testEquals() {
        // create two customers with identical details
        Customer custA = new Customer("alice wonderland", "alice@example.com", "1112223333");
        Customer custB = new Customer("alice wonderland", "alice@example.com", "1112223333");
        assertTrue(custA.equals(custB), "customers with same details should be equal");

        // create a customer with different phone number
        Customer custC = new Customer("alice wonderland", "alice@example.com", "9998887777");
        assertFalse(custA.equals(custC), "customers with different phone should not be equal");
    }

    /**
     * test compareto method based on last name
     */
    @Test
    public void testCompareTo() {
        // compare customers with different last names
        Customer cust1 = new Customer("jim lahey", "jim@lahey.com", "1234567890");
        Customer cust2 = new Customer("john doe", "john@doe.com", "0987654321");
        // since "doe" comes before "lahey", cust1.compareTo(cust2) should be positive
        assertTrue(cust1.compareTo(cust2) > 0, "jim lahey should come after john doe in ordering");

        // compare customers with same last name
        Customer cust3 = new Customer("jane smith", "jane@smith.com", "1111111111");
        Customer cust4 = new Customer("jack smith", "jack@smith.com", "2222222222");
        // both have last name 'smith', so ordering should be equal
        assertEquals(0, cust3.compareTo(cust4), "customers with same last name should compare equal");
    }
}
