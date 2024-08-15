package org.example.taskmanagementsystem.Exception;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException() {
        super();
    }

    public UserNotExistsException(String message) {
        super(message);
    }

    public UserNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotExistsException(Throwable cause) {
        super(cause);
    }

    protected UserNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
