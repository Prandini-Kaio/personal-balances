package com.prandini.personal.conta.service;


import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.conta.model.ContaInput;
import com.prandini.personal.conta.repository.ContaRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ContaUpdater {

    @Resource
    private ContaRepository repository;

    public Conta update(ContaInput input){
        return repository.getReferenceById(input.getId());
    }
}
