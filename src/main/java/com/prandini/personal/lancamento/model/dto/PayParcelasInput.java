package com.prandini.personal.lancamento.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayParcelasInput {
    @NotBlank
    private String banco;
    @NotBlank
    private String conta;
    @NotNull
    private Long lancamentoID;
    @NotNull
    private BigDecimal valor;
}
