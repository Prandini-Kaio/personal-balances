package com.prandini.personal.banco.repository;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findByConta(Conta conta);
}
