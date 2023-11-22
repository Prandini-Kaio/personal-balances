package com.prandini.personal.lancamento.service.parcela;

import com.prandini.personal.lancamento.domain.Parcela;
import com.prandini.personal.lancamento.repository.ParcelaRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParcelaGetter {

    @Resource
    private ParcelaRepository repository;

    public Parcela getById(Long id){
        return repository.getReferenceById(id);
    }
}
