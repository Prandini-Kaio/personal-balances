package com.prandini.personal.lancamento.repository;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;

import java.util.stream.Stream;

public interface LancamentoRepositoryCustom {

    Stream<Lancamento> findStreamByCSVFilter(LancamentoFilter filter);
}
