package core.exeption;

public class PermanentUiException extends UiException {

    public PermanentUiException(String message) {
        super(message);
    }

    public PermanentUiException(String message, Throwable cause) {
        super(message, cause);
    }
}