package core.exeption;


public abstract class UiException extends RuntimeException {

    protected UiException(String message) {
        super(message);
    }

    protected UiException(String message, Throwable cause) {
        super(message, cause);
    }
}