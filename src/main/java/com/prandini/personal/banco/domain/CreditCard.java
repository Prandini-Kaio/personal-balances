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
    @OneToOne
    private Conta conta;
    private BigDecimal limite;
    private BigDecimal limiteUtilizado;
    private int diaVencimento;
    @OneToMany
    private List<Lancamento> lancamentosSaida;

    public void addLancamento(Lancamento lancamento) {
        for(var l : lancamentosSaida){
            limiteUtilizado = limiteUtilizado.add(l.getValor());
        }

        if(limiteUtilizado.compareTo(limite) > 0){
            throw new CreditCardException(CredCardExceptionMessage.limiteExcedido());
        }
    }
}
