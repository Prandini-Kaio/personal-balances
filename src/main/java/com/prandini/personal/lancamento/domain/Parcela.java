package com.prandini.personal.lancamento.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "parcela")
@Table(name = "parcelas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private BigDecimal payedValue;
    private BigDecimal remainingValue;
    private int numero;
    private LocalDate dataVencimento;
    private Boolean payed = false;
}
