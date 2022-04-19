package com.automatski.ocenjivac.studenata.boot.error;

public class SourceCodeNotFoundException extends Exception {
    public SourceCodeNotFoundException() {
    }

    public SourceCodeNotFoundException(String message) {
        super(message);
    }

    public SourceCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceCodeNotFoundException(Throwable cause) {
        super(cause);
    }

    public SourceCodeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
