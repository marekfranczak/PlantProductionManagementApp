package garden.database.rest.exceptions;

/**
 * CLass extends by RuntimeException class.
 * @author Marek Frańczak
 * @since 2.0.0
 * @see RuntimeException
 */
public class DataNotFoundException extends RuntimeException{


    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
