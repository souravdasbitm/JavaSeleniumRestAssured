package exception;

public class PropertyLoadException extends RuntimeException {
    public PropertyLoadException(String message) {
        super(message);
    }

    public PropertyLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
