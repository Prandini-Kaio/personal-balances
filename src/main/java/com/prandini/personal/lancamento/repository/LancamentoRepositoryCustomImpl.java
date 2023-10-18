package com.prandini.personal.lancamento.repository;

import com.prandini.personal.common.QueryUtils;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LancamentoRepositoryCustomImpl implements LancamentoRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Stream<Lancamento> findStreamByCSVFilter(LancamentoFilter filter) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT l ")
                .append("FROM lancamento AS l ")
                .append("WHERE 1 = 1 ");

        QueryUtils.safeAddParams(params, "valor", filter.getId(), sb, "AND l.id = :valor ");

        sb.append("ORDER BY l.data ASC ");

        Query query = this.entityManager.createQuery(sb.toString());
        params.forEach(query::setParameter);

        return query.getResultStream();
    }
}
