package com.prandini.personal.lancamento.repository;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.enums.CategoriaLancamento;

import java.util.List;
import java.util.stream.Stream;

public interface LancamentoRepositoryCustom {
    List<Lancamento> byConta(String conta);
    List<Lancamento> findByCategoria(CategoriaLancamento categoria);
    Stream<Lancamento> byFilter(LancamentoFilter filter);
    List<Object[]> byMes(Integer mes);
    List<Lancamento> byBancoAndConta(String conta, String banco);
    Lancamento byId(Long id);
}
