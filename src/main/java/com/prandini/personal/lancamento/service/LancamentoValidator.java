package com.prandini.personal.lancamento.service;

import com.prandini.personal.conta.service.ContaService;
import com.prandini.personal.lancamento.exceptions.LancamentoException;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LancamentoValidator {

    @Resource
    private ContaService contaService;

    public void validar(LancamentoInput lancamento) throws LancamentoException {
        if(lancamento.getValor().compareTo(BigDecimal.ZERO) <= 0){
            throw new LancamentoException("Valor do lancamento negativo ou zerado.");
        }

        if(contaService.getIfExists(lancamento.getContaId()) == null){
            throw new LancamentoException("Conta inexistente.");
        }
    }
}
