package com.prandini.personal.lancamento.exceptions;

public class LancamentoExceptionMessages {

    public static String valorNegativo(){
        return "VALOR DO LANCAMENTO ZERO OU NEGATIVO.";
    }

    public static String contaNotFound(){
        return "Conta não encontrada";
    }
    public static String categoriaEntradaInvalida(){return "Categoria do Lançamento, não se encaixa nas categorias de entrada."; }
    public static String categoriaSaidaInvalida(){return "Categoria do Lançamento, não se encaixa nas categorias de saída."; }
}
