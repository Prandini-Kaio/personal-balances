package com.prandini.personal.banco.service;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.domain.converter.ContaConverter;
import com.prandini.personal.banco.model.ContaInput;
import com.prandini.personal.banco.model.ContaOutput;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
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

    public Conta byId(Long id){
        return getter.findById(id);
    }

    public void addLancamento(Conta conta, Lancamento lancamento){
        conta.addLancamento(lancamento);
        if(lancamento.getTipoLancamento().equals(TipoLancamento.ENTRADA)){
            conta.sumValueOn(lancamento.getValor());
            return;
        }

        conta.getCreditCard().addLancamento(lancamento);
    }

    public Page<ContaOutput> getAll(Pageable pageable) {
        return getter.getContas(pageable);
    }

    public ContaOutput desactive(Long id){
        return ContaConverter.toOutput(updater.desactive(id));
    }
}
