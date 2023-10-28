package com.prandini.personal.lancamento.model;

import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class LancamentoInput {
    private Long id;
    @NotBlank
    private String banco;
    @NotBlank
    private String conta;
    @NotNull
    private BigDecimal valor;
    @NotBlank
    private String descricao;
    @NotNull
    private CategoriaLancamento categoriaLancamento;
    @NotNull
    private TipoLancamento tipoLancamento;
    private int parcelas;
}
