package com.prandini.personal.banco.service.banco;

import com.prandini.personal.banco.domain.Banco;
import com.prandini.personal.banco.model.banco.BancoInput;
import com.prandini.personal.banco.model.banco.BancoOutput;
import com.prandini.personal.banco.repository.BancoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BancoRegister {

    @Resource
    private BancoRepository repository;

    public Banco create(BancoInput input){
        Banco banco = new Banco();
        banco.setName(input.getName());
        return repository.save(banco);
    }
}
