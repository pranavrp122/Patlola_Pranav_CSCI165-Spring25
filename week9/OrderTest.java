import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * this class tests the order class for privacy leaks.
 * it checks that getdate, getcustomer, and getcart return deep copies.
 */
public class OrderTest {

    /**
     * helper method to create a sample order.
     * it creates a customer, a menuitem and adds an orderitem to the order.
     * @return a sample order.
     */
    private Order createSampleOrder() {
        Customer cust = new Customer("jim", "lahey");
        Order order = new Order(cust);
        MenuItem item = new MenuItem("freedom fries", 2.99, 448);
        order.addItem(item, 2);
        return order;
    }
    
    /**
     * tests that getdate returns a deep copy.
     * verifies that modifying the returned date does not affect the original order.
     */
    @Test
    public void testGetDateDeepCopy() {
        Order order = createSampleOrder();
        Date dateCopy1 = order.getDate();
        Date dateCopy2 = order.getDate();
        
        // both copies should be equal in state
        assertEquals(dateCopy1.toString(), dateCopy2.toString(), "dates should be equal in state");
        // they must be different objects (different memory references)
        assertNotSame(dateCopy1, dateCopy2, "date copies must be different objects (deep copy)");

        // modify one copy and check that subsequent call to getDate returns the original value
        int originalDay = dateCopy2.getDay();
        dateCopy1.setDay(originalDay + 1);
        Date dateCopy3 = order.getDate();
        assertEquals(originalDay, dateCopy3.getDay(), "modifying the copy must not affect the order's internal date");
    }
    
    /**
     * tests that getcustomer returns a deep copy.
     * since customer is immutable in this example (only getters provided),
     * we check that multiple calls return separate objects that are equal in state.
     */
    @Test
    public void testGetCustomerDeepCopy() {
        Order order = createSampleOrder();
        Customer customerCopy1 = order.getCustomer();
        Customer customerCopy2 = order.getCustomer();
        
        // check that both customer copies represent the same state
        assertEquals(customerCopy1.toString(), customerCopy2.toString(), "customers should be equal in state");
        // check that they are not the same reference
        assertNotSame(customerCopy1, customerCopy2, "customer copies must be deep copies (different objects)");
    }
    
    /**
     * tests that getcart returns a deep copy.
     * verifies that modifying the returned cart does not affect the order's internal cart.
     */
    @Test
    public void testGetCartDeepCopy() {
        Order order = createSampleOrder();
        ArrayList<OrderItem> cartCopy1 = order.getCart();
        ArrayList<OrderItem> cartCopy2 = order.getCart();
        
        // verify same number of items
        assertEquals(cartCopy1.size(), cartCopy2.size(), "carts should have the same number of items");
        if (!cartCopy1.isEmpty()) {
            OrderItem itemCopy1 = cartCopy1.get(0);
            OrderItem itemCopy2 = cartCopy2.get(0);
            // check that orderitems are equal in state but not the same object
            assertTrue(itemCopy1.equals(itemCopy2), "orderitems should be equal in state");
            assertNotSame(itemCopy1, itemCopy2, "orderitems should be deep copies");
        }
        
        // modify one copy of the cart
        if (!cartCopy1.isEmpty()) {
            int originalQuantity = cartCopy1.get(0).getQuantity();
            cartCopy1.get(0).updateQuantity(3); // this changes the copy but not the internal cart of order
            
            // now get a fresh copy of the cart from the order
            ArrayList<OrderItem> cartCopy3 = order.getCart();
            int freshQuantity = cartCopy3.get(0).getQuantity();
            assertEquals(originalQuantity, freshQuantity, "modifying the returned cart should not affect the order's internal cart");
        }
    }
}
