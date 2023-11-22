package com.prandini.personal.lancamento.domain;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "lancamento")
@Table(name = "lancamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal;
    private BigDecimal valorTotalPago;
    private LocalDateTime data;
    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Conta conta;

    @Enumerated(EnumType.STRING)
    private CategoriaLancamento categoriaLancamento;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Parcela> parcelas;

    private boolean ativa = true;
}
