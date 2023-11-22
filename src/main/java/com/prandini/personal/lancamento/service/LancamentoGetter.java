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
import java.math.RoundingMode;
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

    public List<Lancamento> byContaAndBanco(String conta, String banco){
        return repository.byBancoAndConta(conta, banco);
    }

    public Lancamento byId(Long id){
        return repository.byId(id);
    }

    public Stream<Lancamento> byFilter(LancamentoFilter filter){
        return repository.byFilter(filter);
    }

    public List<CostOfMonthDTO> costByMes(Integer mes){
        List<Object[]> result = repository.byMes(mes);
        List<CostOfMonthDTO> dtos = new ArrayList<>();
        for(var row: result){
            int month = (Integer)row[0];
            BigDecimal total = (BigDecimal)row[1];
            BigDecimal avg = new BigDecimal(String.valueOf(row[2])).setScale(2, RoundingMode.CEILING);

            dtos.add(new CostOfMonthDTO(month, total, avg));
        }
        return dtos;
    }

}
