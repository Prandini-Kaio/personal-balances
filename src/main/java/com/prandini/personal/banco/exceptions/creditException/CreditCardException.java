package com.prandini.personal.banco.exceptions.creditException;

public class CreditCardException extends RuntimeException {
    public CreditCardException() {
        super();
    }

    public CreditCardException(String message) {
        super(message);
    }

    public CreditCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreditCardException(Throwable cause) {
        super(cause);
    }

    protected CreditCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
