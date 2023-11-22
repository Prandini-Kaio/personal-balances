package com.prandini.personal.banco.domain;

import com.prandini.personal.banco.service.LancamentoValidator;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
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
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;
    private Boolean active = true;

    public void addLancamento(Lancamento lancamento){
        if(lancamento.getTipoLancamento() == TipoLancamento.ENTRADA)
            valueOn = valueOn.add(lancamento.getValorTotal());
        else
            creditCard.addLancamento(lancamento);

        lancamentos.add(lancamento);
    }
}
