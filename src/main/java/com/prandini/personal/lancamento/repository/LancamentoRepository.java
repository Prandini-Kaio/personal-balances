package com.prandini.personal.lancamento.repository;

import com.prandini.personal.lancamento.domain.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryCustom {
    Page<Lancamento> findAllByAtivaIsTrue(Pageable pageable);
}
