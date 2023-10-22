package com.prandini.personal.lancamento.domain.filter;

import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
public class LancamentoFilter {

    private Long id;
    private String conta;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;
    private CategoriaLancamento categoria;
    private TipoLancamento tipo;
}
