package com.prandini.personal.lancamento.domain.converter;

import com.prandini.personal.banco.domain.converter.CreditCardConverter;
import com.prandini.personal.common.LocalDateConverter;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.model.dto.CostOfMonthDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class LancamentoConverter {
    public static LancamentoOutput toOutput(Lancamento lancamento){
        return new LancamentoOutput(
                lancamento.getId(),
                lancamento.getConta().getBanco().getName(),
                lancamento.getConta().getName(),
                LocalDateConverter.toBrazilianDateString(lancamento.getData()),
                lancamento.getDescricao(),
                lancamento.getCategoriaLancamento(),
                lancamento.getTipoLancamento(),
                ParcelaConverter.toOutput(lancamento.getParcelas())
        );
    }

    public static Page<LancamentoOutput> toOutput(Page<Lancamento> contas){
        return contas.map(LancamentoConverter::toOutput);
    }

    public static Stream<LancamentoOutput> toOutput(Stream<Lancamento> lancamentos){
        return lancamentos.map(LancamentoConverter::toOutput);
    }

    public static List<LancamentoOutput> toOutput(List<Lancamento> lancamentos){
        return lancamentos.stream().map(LancamentoConverter::toOutput).toList();
    }
}
