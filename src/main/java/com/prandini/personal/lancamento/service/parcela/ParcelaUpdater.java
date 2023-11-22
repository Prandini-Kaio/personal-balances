package com.prandini.personal.lancamento.service.parcela;

import com.prandini.personal.lancamento.domain.Parcela;
import com.prandini.personal.lancamento.model.dto.ParcelaInput;
import com.prandini.personal.lancamento.repository.ParcelaRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ParcelaUpdater {

    @Resource
    private ParcelaRepository repository;

    public Parcela pay(ParcelaInput input){
        Parcela parcela = repository.findById(input.getId()).orElseThrow();

        parcela.setPayedValue(parcela.getPayedValue().add(input.getValor()));
        parcela.setPayed(true);

        return repository.save(parcela);
    }
}
