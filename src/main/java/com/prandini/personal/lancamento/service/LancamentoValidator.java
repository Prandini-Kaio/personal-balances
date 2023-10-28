package com.prandini.personal.lancamento.service;

import com.prandini.personal.banco.service.ContaService;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.enums.TipoLancamento;
import com.prandini.personal.lancamento.exceptions.LancamentoException;
import com.prandini.personal.lancamento.exceptions.LancamentoExceptionMessages;
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
        validaEntrada(lancamento);
        validaSaida(lancamento);
    }

    public void validaValor(LancamentoInput input){
        if(input.getValor().compareTo(BigDecimal.ZERO) <= 0){
            throw new LancamentoException(LancamentoExceptionMessages.valorNegativo());
        }
    }

    public void validaEntrada(LancamentoInput lancamento){
        if(lancamento.getTipoLancamento() != TipoLancamento.ENTRADA)
            return;

        if(lancamento.getCategoriaLancamento() != CategoriaLancamento.RENDA
                && lancamento.getCategoriaLancamento() != CategoriaLancamento.RENDA_ADICIONAL
                && lancamento.getCategoriaLancamento() != CategoriaLancamento.ECONOMIA)
        {
            throw new LancamentoException(LancamentoExceptionMessages.categoriaEntradaInvalida());
        }
    }

    public void validaSaida(LancamentoInput lancamento){
        if(lancamento.getTipoLancamento() != TipoLancamento.SAIDA)
            return;

        if(lancamento.getCategoriaLancamento() == CategoriaLancamento.RENDA
                || lancamento.getCategoriaLancamento() == CategoriaLancamento.RENDA_ADICIONAL
                || lancamento.getCategoriaLancamento() == CategoriaLancamento.ECONOMIA)
        {
            throw new LancamentoException(LancamentoExceptionMessages.categoriaSaidaInvalida());
        }
    }
}
