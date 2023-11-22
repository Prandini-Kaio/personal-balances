package com.prandini.personal.lancamento.model;


import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import com.prandini.personal.lancamento.model.dto.ParcelaOuput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoOutput {
    private Long id;
    private String banco;
    private String conta;
    private String data;
    private String description;
    private CategoriaLancamento categoriaLancamento;
    private TipoLancamento tipoLancamento;
    private List<ParcelaOuput> parcelas;
}
