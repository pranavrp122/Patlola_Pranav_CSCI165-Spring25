// exception thrown when overdraft or withdrawal limits are exceeded
/**
 * overdraftexception thrown if overdraft or withdrawal limit is exceeded
 */
public class OverdraftException extends Exception {
    // default constructor
    public OverdraftException() {
        super();
    }
    // constructor with message
    public OverdraftException(String msg) {
        super(msg);
    }
}
