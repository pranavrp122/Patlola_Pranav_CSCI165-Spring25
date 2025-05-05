// exception thrown when account number is not exactly 5 digits
/**
 * invalidaccountnumberexception thrown if acct number fails policy
 */
public class InvalidAccountNumberException extends Exception {
    // default constructor
    public InvalidAccountNumberException() {
        super();
    }
    // constructor with message
    public InvalidAccountNumberException(String msg) {
        super(msg);
    }
}
