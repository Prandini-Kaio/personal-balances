package com.prandini.personal.conta.domain.converter;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.conta.model.ContaOutput;
import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import org.springframework.data.domain.Page;

public class ContaConverter {

    public static ContaOutput toOutput(Conta conta){
        return new ContaOutput(conta.getId(), conta.getName(), conta.getActive(), LancamentoConverter.toOutput(conta.getLancamentos()));
    }

    public static Page<ContaOutput> toOutput(Page<Conta> contas){
        return contas.map(c -> new ContaOutput(c.getId(), c.getName(), c.getActive(), LancamentoConverter.toOutput(c.getLancamentos())));
    }
}
