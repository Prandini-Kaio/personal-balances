package com.prandini.personal.banco.domain;

import com.prandini.personal.banco.exceptions.creditException.CredCardExceptionMessage;
import com.prandini.personal.banco.exceptions.creditException.CreditCardException;
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
    @OneToOne(cascade = CascadeType.ALL)
    private Conta conta;
    private BigDecimal limite = BigDecimal.ZERO;
    private BigDecimal limiteUtilizado = BigDecimal.ZERO;
    private int diaVencimento;
    @OneToMany
    private List<Lancamento> lancamentosSaida;
}
