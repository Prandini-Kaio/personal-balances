package com.prandini.personal.banco.repository;

import com.prandini.personal.banco.domain.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Page<Conta> findByActiveIsTrue(Pageable pageable);

    @Query("""
            SELECT c FROM conta c
            JOIN c.banco b
            WHERE b.name = :banco AND c.name = :conta
        """)
    Conta findContaByBanco(String banco, String conta);

}
