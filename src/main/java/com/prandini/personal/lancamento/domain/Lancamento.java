package com.prandini.personal.lancamento.domain;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "lancamento")
@Table(name = "lancamentos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private LocalDateTime data;
    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @Enumerated(EnumType.STRING)
    private CategoriaLancamento categoriaLancamento;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    @Setter
    private boolean ativa;
}
