package com.prandini.personal.banco.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContaInput {
    @NotNull
    private String banco;
    @NotBlank
    private String name;
    private BigDecimal limite;
    private int diaVencimento;
}
