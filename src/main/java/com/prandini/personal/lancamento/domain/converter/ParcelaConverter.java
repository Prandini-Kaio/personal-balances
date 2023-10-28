package com.prandini.personal.lancamento.domain.converter;

import com.prandini.personal.lancamento.domain.Parcela;
import com.prandini.personal.lancamento.model.ParcelaOuput;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParcelaConverter {

    public static ParcelaOuput toOutput(Parcela parcela){
        return new ParcelaOuput(
                parcela.getId(),
                parcela.getValor(),
                parcela.getNumero(),
                parcela.getDataVencimento(),
                parcela.getPayed()
        );
    }

    public static List<ParcelaOuput> toOutput(List<Parcela> parcelas){
        return parcelas.stream().map(ParcelaConverter::toOutput).toList();
    }
}
