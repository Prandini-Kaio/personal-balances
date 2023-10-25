package com.prandini.personal.banco.service.creditCard;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.domain.CreditCard;
import com.prandini.personal.banco.repository.CreditCardRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class CreditCardGetter {

    @Resource
    private CreditCardRepository repository;

    public CreditCard byConta(Conta conta){
        return repository.findByConta(conta);
    }
}
