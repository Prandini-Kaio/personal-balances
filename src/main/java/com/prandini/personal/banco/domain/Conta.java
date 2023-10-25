package com.prandini.personal.banco.domain;

import com.prandini.personal.banco.service.LancamentoValidator;
import com.prandini.personal.lancamento.domain.Lancamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "conta")
@Table(name = "contas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal valueOn;
    @OneToOne(cascade = CascadeType.ALL)
    private CreditCard creditCard;
    @ManyToOne
    private Banco banco;
    @OneToMany
    private List<Lancamento> lancamentos;
    private Boolean active;

    public void addLancamento(Lancamento lancamento){
        LancamentoValidator.validar(lancamento);
        this.lancamentos.add(lancamento);
    }

    public void sumValueOn(BigDecimal value){
        this.valueOn = this.valueOn.add(value);
    }
}
