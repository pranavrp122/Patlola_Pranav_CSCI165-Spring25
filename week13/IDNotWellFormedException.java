// simple exception thrown when customer id is not letter + 3 digits
/**
 * idnotwellformedexception thrown if cust id fails policy
 */
public class IDNotWellFormedException extends Exception {
    // default constructor
    public IDNotWellFormedException() {
        super();
    }
    // constructor with message
    public IDNotWellFormedException(String msg) {
        super(msg);
    }
}
