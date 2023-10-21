package com.prandini.personal.conta.service;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.conta.domain.converter.ContaConverter;
import com.prandini.personal.conta.model.ContaInput;
import com.prandini.personal.conta.model.ContaOutput;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    @Resource
    private ContaRegister register;
    @Resource
    private ContaGetter getter;
    @Resource
    private ContaUpdater updater;

    public ContaOutput register(ContaInput input){
        return this.register.register(input);
    }

    public Conta getIfExists(Long id){
        return getter.getIfExists(id);
    }

    public Page<ContaOutput> getAll(Pageable pageable) {
        return getter.getContas(pageable);
    }

    public ContaOutput update(ContaInput input){
        return ContaConverter.toOutput(this.updater.update(input));
    }

    public ContaOutput desactive(Long id){
        return ContaConverter.toOutput(updater.desactive(id));
    }
}
