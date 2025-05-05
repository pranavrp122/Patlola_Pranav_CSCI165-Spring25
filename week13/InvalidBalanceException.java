// exception thrown when a deposit or withdrawal violates balance policy
/**
 * invalidbalanceexception thrown if balance policy is violated
 */
public class InvalidBalanceException extends Exception {
    // default constructor
    public InvalidBalanceException() {
        super();
    }
    // constructor with message
    public InvalidBalanceException(String msg) {
        super(msg);
    }
}
