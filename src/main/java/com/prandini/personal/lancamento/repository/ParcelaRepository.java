package com.prandini.personal.lancamento.repository;

import com.prandini.personal.lancamento.domain.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {


}
