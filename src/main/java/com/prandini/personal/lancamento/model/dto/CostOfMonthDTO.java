package com.prandini.personal.lancamento.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostOfMonthDTO {

    private BigDecimal custoTotal;
    private int mes;
    private BigDecimal media;
}
