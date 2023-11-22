package com.prandini.personal.lancamento.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "parcela")
@Table(name = "parcelas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private BigDecimal payedValue = BigDecimal.ZERO;
    private BigDecimal remainingValue = valor;
    private int numero;
    private LocalDate dataVencimento;
    private Boolean payed = false;
}
