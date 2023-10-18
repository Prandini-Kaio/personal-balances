package com.prandini.personal.lancamento.model;

import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class LancamentoInput {
    private Long id;
    @NotNull
    private Long contaId;
    @NotNull
    private BigDecimal valor;
    private String descricao;
    @NotNull
    private CategoriaLancamento categoriaLancamento;
    @NotNull
    private TipoLancamento tipoLancamento;
}
