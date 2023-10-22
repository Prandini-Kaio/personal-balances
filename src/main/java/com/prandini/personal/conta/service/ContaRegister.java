package com.prandini.personal.conta.service;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.conta.domain.converter.ContaConverter;
import com.prandini.personal.conta.model.ContaInput;
import com.prandini.personal.conta.model.ContaOutput;
import com.prandini.personal.conta.repository.ContaRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ContaRegister {

    @Resource
    private ContaRepository repository;

    public ContaOutput register(ContaInput input){
        Conta conta = new Conta(null, input.getName(), true, new ArrayList<>());
        repository.save(conta);
        return ContaConverter.toOutput(conta);
    }
}
