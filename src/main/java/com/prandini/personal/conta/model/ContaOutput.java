package com.prandini.personal.conta.model;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContaOutput {
    private Long id;
    private String name;
    private Boolean active;
    private List<LancamentoOutput> lancamentoList;
}
