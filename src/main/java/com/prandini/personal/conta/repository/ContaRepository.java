package com.prandini.personal.conta.repository;

import com.prandini.personal.conta.domain.Conta;
import com.prandini.personal.conta.model.ContaOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Page<Conta> findByActiveIsTrue(Pageable pageable);
}
