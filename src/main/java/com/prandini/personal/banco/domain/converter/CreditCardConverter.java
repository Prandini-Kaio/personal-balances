package com.prandini.personal.banco.domain.converter;

import com.prandini.personal.banco.domain.CreditCard;
import com.prandini.personal.banco.model.creditCard.CreditCardOutput;

public class CreditCardConverter {

    public static CreditCardOutput toOutput(CreditCard card){
        return new CreditCardOutput(card.getConta().getName(), card.getLimite(), card.getLimiteUtilizado(), card.getDiaVencimento());
    }
}
