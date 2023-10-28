package com.prandini.personal.banco.model;

import com.prandini.personal.lancamento.model.LancamentoOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContaOutput {
    private Long id;
    private String banco;
    private String name;
    private BigDecimal valueOn;
    private Boolean active;
    //private List<LancamentoOutput> lancamentoList = new ArrayList<>();
}
