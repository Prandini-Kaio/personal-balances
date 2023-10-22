package com.prandini.personal.lancamento.service;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.conta.repository.ContaRepository;
import com.prandini.personal.conta.service.ContaRegister;
import com.prandini.personal.conta.service.ContaService;
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
        Conta conta = contaService.getIfExists(input.getContaId());

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
        conta.addLancamento(lancamento);
        repository.save(lancamento);

        return LancamentoConverter.toOutput(lancamento);
    }
}
