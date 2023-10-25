package com.prandini.personal.banco.domain.converter;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.model.ContaOutput;
import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import org.springframework.data.domain.Page;

public class ContaConverter {

    public static ContaOutput toOutput(Conta conta){
        return new ContaOutput(conta.getId(), conta.getBanco().getName(), conta.getName(), conta.getValueOn(), conta.getActive(), LancamentoConverter.toOutput(conta.getLancamentos()));
    }

    public static Page<ContaOutput> toOutput(Page<Conta> contas){
        return contas.map(ContaConverter::toOutput);
    }
}
