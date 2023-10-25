package com.prandini.personal.conta.domain;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    private Banco banco;

    @OneToMany()
    private List<Lancamento> lancamentos;

    private Boolean active;

    public void addLancamento(Lancamento lancamento){
        lancamentos.add(lancamento);
    }
}
