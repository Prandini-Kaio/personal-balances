package com.prandini.personal.lancamento.repository;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.model.dto.CostOfMonthDTO;

import java.util.List;
import java.util.stream.Stream;

public interface LancamentoRepositoryCustom {

    List<Lancamento> findByConta(String conta);
    Stream<Lancamento> findStreamByFilter(LancamentoFilter filter);
    List<Object[]> findByMes(Integer mes);
}
