package com.prandini.personal.lancamento.service;

import com.prandini.personal.banco.service.ContaService;
import com.prandini.personal.lancamento.exceptions.LancamentoException;
import com.prandini.personal.lancamento.model.LancamentoInput;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LancamentoValidator {

    @Resource
    private ContaService contaService;

    public void execute(LancamentoInput lancamento) throws LancamentoException {
        validaValor(lancamento);
    }

    public void validaValor(LancamentoInput input){
        if(input.getValor().compareTo(BigDecimal.ZERO) <= 0){
            throw new LancamentoException("Valor do lancamento negativo ou zerado");
        }
    }
}
