package com.prandini.personal.banco.service.banco;

import com.prandini.personal.banco.domain.Banco;
import com.prandini.personal.banco.exceptions.BancoException;
import com.prandini.personal.banco.repository.BancoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BancoGetter {

    @Resource
    private BancoRepository repository;

    public Banco byName(String name){
        return repository.findByName(name).stream().findFirst().orElseThrow();
    }

}
