package com.prandini.personal.lancamento.exceptions;

public class ParcelaException extends RuntimeException{
    public ParcelaException() {
        super();
    }

    public ParcelaException(String message) {
        super("ERRO PARCELA: " + message);
    }

    public ParcelaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParcelaException(Throwable cause) {
        super(cause);
    }

    protected ParcelaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
