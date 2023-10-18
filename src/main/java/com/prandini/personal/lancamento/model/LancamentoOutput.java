package com.prandini.personal.lancamento.model;


import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoOutput {
    private Long id;
    private BigDecimal valor;
    private LocalDateTime data;
    private String description;
    private CategoriaLancamento categoriaLancamento;
    private TipoLancamento tipoLancamento;

    public LancamentoOutput(Lancamento lancamento){
        this.id = lancamento.getId();
        this.valor = lancamento.getValor();
        this.data = lancamento.getData();
        this.description = lancamento.getDescricao();
        this.categoriaLancamento = lancamento.getCategoriaLancamento();
        this.tipoLancamento = lancamento.getTipoLancamento();
    }
}
