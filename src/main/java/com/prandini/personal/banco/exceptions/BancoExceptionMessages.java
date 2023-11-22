package com.prandini.personal.banco.exceptions;

import com.prandini.personal.banco.domain.Banco;

public class BancoExceptionMessages {

    private static final String notFoundMessage = "%s não encontrado na base de dados.";

    public static String notFound(Banco banco){
        return String.format(notFoundMessage, banco.getName());
    }
}
