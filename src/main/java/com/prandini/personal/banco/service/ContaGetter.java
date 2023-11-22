package com.prandini.personal.banco.service;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.domain.converter.ContaConverter;
import com.prandini.personal.banco.model.ContaOutput;
import com.prandini.personal.banco.repository.ContaRepository;
import jakarta.annotation.Resource;
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

    public Conta findById(Long id){
        return repository.findById(id).orElseThrow();
    }
    public Conta findByBancoAndConta(String banco, String conta){
        return repository.findContaByBanco(banco, conta);
    }
}
