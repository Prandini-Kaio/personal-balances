package com.prandini.personal.lancamento.service;

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

    public Lancamento update(LancamentoInput input){
        Lancamento lancamento = repository.getReferenceById(input.getId());

        lancamento.setDescricao(input.getDescricao());
        lancamento.setCategoriaLancamento(input.getCategoriaLancamento());
        lancamento.setTipoLancamento(input.getTipoLancamento());

        return lancamento;
    }

    public Lancamento desactive(Long id){
        Lancamento lancamento = repository.getReferenceById(id);
        lancamento.setAtiva(false);
        return lancamento;
    }
}
