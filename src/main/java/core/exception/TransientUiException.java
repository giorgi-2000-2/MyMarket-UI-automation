package core.exception;

public class TransientUiException extends UiException {

    public TransientUiException(String message) {
        super(message);
    }

    public TransientUiException(String message, Throwable cause) {
        super(message, cause);
    }
}