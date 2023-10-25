package com.prandini.personal.banco.service;


import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.model.ContaInput;
import com.prandini.personal.banco.repository.ContaRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ContaUpdater {

    @Resource
    private ContaRepository repository;

    public Conta desactive(Long id){
        Conta conta = repository.getReferenceById(id);
        conta.setActive(false);
        return conta;
    }
}
