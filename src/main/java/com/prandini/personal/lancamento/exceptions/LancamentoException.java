package com.prandini.personal.lancamento.exceptions;

import org.hibernate.tool.schema.spi.ExceptionHandler;

public class LancamentoException extends RuntimeException {
    public LancamentoException(String message) {
        super("ERRO LANCAMENTO: " + message);
    }

    public LancamentoException() {
        super();
    }

    public LancamentoException(String message, Throwable cause) {
        super(message, cause);
    }

    public LancamentoException(Throwable cause) {
        super(cause);
    }

    protected LancamentoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
