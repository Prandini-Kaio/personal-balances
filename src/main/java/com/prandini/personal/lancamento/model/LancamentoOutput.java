package com.prandini.personal.lancamento.model;


import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoOutput {
    private Long id;
    private BigDecimal valor;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime data;
    private String description;
    private CategoriaLancamento categoriaLancamento;
    private TipoLancamento tipoLancamento;
}
