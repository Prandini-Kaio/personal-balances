package com.prandini.personal.lancamento.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelaOuput {
    private Long id;
    private BigDecimal valor;
    private int numero;
    private LocalDate dataVencimento;
    private Boolean payed = false;
}
