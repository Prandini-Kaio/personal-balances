package com.prandini.personal.lancamento.service;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.model.dto.CostOfMonthDTO;
import com.prandini.personal.lancamento.repository.LancamentoRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        List<Object[]> result = repository.findByMes(mes);
        List<CostOfMonthDTO> dtos = new ArrayList<>();
        for(var row: result){
            int month = (Integer)row[0];
            BigDecimal total = (BigDecimal)row[1];
            Double avg = (Double) row[2];

            dtos.add(new CostOfMonthDTO(month, total, avg));
        }
        return dtos;
    }

}
