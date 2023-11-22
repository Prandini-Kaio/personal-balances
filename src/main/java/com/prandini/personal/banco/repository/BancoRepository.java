package com.prandini.personal.banco.repository;

import com.prandini.personal.banco.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, Long> {

    @Query("SELECT b FROM banco b WHERE b.name = :name")
    Optional<Banco> findByName(String name);
}
