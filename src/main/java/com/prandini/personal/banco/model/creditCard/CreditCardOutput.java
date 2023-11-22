package com.prandini.personal.banco.model.creditCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardOutput {
    private String conta;
    private BigDecimal limite;
    private BigDecimal limiteUtilizado;
    private int diaVencimento;
}
