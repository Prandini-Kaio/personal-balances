package com.prandini.personal.lancamento.domain.converter;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class LancamentoConverter {

    public static Page<LancamentoOutput> toOutput(Page<Lancamento> contas){
        return contas.map(LancamentoOutput::new);
    }

    public static LancamentoOutput toOutput(Lancamento lancamento){
        return new LancamentoOutput(lancamento);
    }

    public static List<LancamentoOutput> toOutput(List<Lancamento> lancamentos){

        List<LancamentoOutput> lancamentoOutputs = new ArrayList<>();
        lancamentos.forEach(l -> lancamentoOutputs.add(new LancamentoOutput(l)));

        return lancamentoOutputs;
    }
}
