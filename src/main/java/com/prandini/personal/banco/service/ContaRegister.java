package com.prandini.personal.banco.service;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.domain.CreditCard;
import com.prandini.personal.banco.domain.converter.ContaConverter;
import com.prandini.personal.banco.model.ContaInput;
import com.prandini.personal.banco.model.ContaOutput;
import com.prandini.personal.banco.repository.ContaRepository;
import com.prandini.personal.banco.service.banco.BancoGetter;
import com.prandini.personal.banco.service.creditCard.CreditCardGetter;
import com.prandini.personal.banco.service.creditCard.CreditCardRegister;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class ContaRegister {

    @Resource
    private ContaRepository repository;
    @Resource
    private BancoGetter bancoGetter;
    @Resource
    private CreditCardRegister creditCardRegister;

    public ContaOutput register(ContaInput input){
        Conta conta = new Conta();
        conta.setName(input.getName());
        conta.setBanco(bancoGetter.byName(input.getBanco()));

        CreditCard creditCard = new CreditCard();
        creditCard.setLimite(input.getLimite());
        creditCard.setDiaVencimento(input.getDiaVencimento());
        creditCard.setConta(conta);

        conta.setCreditCard(creditCard);
        conta.setValueOn(BigDecimal.ZERO);

        repository.save(conta);
        creditCard.setConta(conta);
        return ContaConverter.toOutput(conta);
    }
}
