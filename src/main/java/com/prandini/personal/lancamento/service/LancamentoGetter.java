package com.prandini.personal.lancamento.service;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.model.dto.CostOfMonthDTO;
import com.prandini.personal.lancamento.repository.LancamentoRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class LancamentoGetter {

    @Resource
    private LancamentoRepository repository;

    public Page<Lancamento> pageAll(Pageable pageable){
        return repository.findAllByAtivaIsTrue(pageable);
    }

    public List<Lancamento> findByConta(String conta){
        return repository.findByConta(conta);
    }

    public Stream<Lancamento> findByFilter(LancamentoFilter filter){
        return repository.findStreamByFilter(filter);
    }

    public List<CostOfMonthDTO> findCostByMes(Integer mes){
        return repository.findByMes(mes);
    }

}
