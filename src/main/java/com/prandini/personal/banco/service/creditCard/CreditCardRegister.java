package com.prandini.personal.banco.service.creditCard;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.domain.CreditCard;
import com.prandini.personal.banco.repository.CreditCardRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditCardRegister {

    @Resource
    private CreditCardRepository repository;

    public CreditCard register(Conta conta, BigDecimal limite, int diaVencimento){
        CreditCard creditCard = new CreditCard();
        creditCard.setDiaVencimento(diaVencimento);
        creditCard.setLimite(limite);
        creditCard.setLimiteUtilizado(BigDecimal.ZERO);
        creditCard.setConta(conta);
        return repository.save(creditCard);
    }
}
