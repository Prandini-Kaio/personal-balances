package com.prandini.personal.conta.domain;

import com.prandini.personal.lancamento.domain.Lancamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "credito")
@Table(name = "creditos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Conta conta;
    private BigDecimal limite;
    private BigDecimal limiteUtilizado;
    private int diaVencimento;
}
