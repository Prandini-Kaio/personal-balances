package com.prandini.personal.lancamento.service;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.repository.LancamentoRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class LancamentoGetter {

    @Resource
    private LancamentoRepository repository;

    public Page<Lancamento> pageAll(Pageable pageable){
        return repository.findAllByAtivaIsTrue(pageable);
    }

    public Stream<Lancamento> getStreamByFilter(LancamentoFilter filter){
        return repository.findStreamByCSVFilter(filter);
    }
}
