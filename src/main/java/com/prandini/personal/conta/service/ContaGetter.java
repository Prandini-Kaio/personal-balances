package com.prandini.personal.conta.service;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.conta.domain.converter.ContaConverter;
import com.prandini.personal.conta.model.ContaOutput;
import com.prandini.personal.conta.repository.ContaRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ContaGetter {

    @Resource
    private ContaRepository repository;

    public Page<ContaOutput> getContas(Pageable pageable){
        return ContaConverter.toOutput(repository.findByActiveIsTrue(pageable));
    }

    public Conta getIfExists(Long id){

        Conta conta = repository.getReferenceById(id);

        if(conta.getId() == null){
            throw new EntityNotFoundException("Conta inválida");
        }

        return conta;
    }
}
