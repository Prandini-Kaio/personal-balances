package com.prandini.personal.lancamento.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ParcelaInput {
    private Long id;
    private Long lancamentoId;
    private String banco;
    private String conta;
    private BigDecimal valor;
}
