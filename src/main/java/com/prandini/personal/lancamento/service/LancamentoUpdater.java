package com.prandini.personal.lancamento.service;

import com.prandini.personal.banco.service.ContaGetter;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.repository.LancamentoRepository;
import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LancamentoUpdater {

    @Resource
    private LancamentoRepository repository;

    @Resource
    private ContaGetter contaGetter;

    public Lancamento update(LancamentoInput input){
        Lancamento lancamento = repository.getReferenceById(input.getId());

        lancamento.setConta(contaGetter.findByBancoAndConta(input.getBanco(), input.getConta()));
        lancamento.setData(LocalDateTime.now());
        lancamento.setDescricao(input.getDescricao());
        lancamento.setTipoLancamento(input.getTipoLancamento());
        lancamento.setCategoriaLancamento(input.getCategoriaLancamento());

        return lancamento;
    }

    public Lancamento desactive(Long id){
        Lancamento lancamento = repository.getReferenceById(id);
        lancamento.setAtiva(false);
        return lancamento;
    }
}
