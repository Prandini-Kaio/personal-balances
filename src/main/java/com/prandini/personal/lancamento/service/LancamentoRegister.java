package com.prandini.personal.lancamento.service;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.service.ContaService;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.repository.LancamentoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LancamentoRegister {

    @Resource
    private LancamentoRepository repository;
    @Resource
    private ContaService contaService;

    public LancamentoOutput register(LancamentoInput input){
        Conta conta = contaService.byId(input.getContaId());

        Lancamento lancamento = new Lancamento(
                null,
                input.getValor(),
                LocalDateTime.now(),
                input.getDescricao(),
                conta,
                input.getCategoriaLancamento(),
                input.getTipoLancamento(),
                true
        );

        contaService.addLancamento(conta, lancamento);
        repository.save(lancamento);

        return LancamentoConverter.toOutput(lancamento);
    }
}
