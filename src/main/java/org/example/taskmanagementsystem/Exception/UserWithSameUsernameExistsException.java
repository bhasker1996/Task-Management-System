package org.example.taskmanagementsystem.Exception;

public class UserWithSameUsernameExistsException extends RuntimeException {
    public UserWithSameUsernameExistsException() {
        super();
    }

    public UserWithSameUsernameExistsException(String message) {
        super(message);
    }

    public UserWithSameUsernameExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWithSameUsernameExistsException(Throwable cause) {
        super(cause);
    }

    protected UserWithSameUsernameExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
