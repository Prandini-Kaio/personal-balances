package com.prandini.personal.lancamento.repository;

import com.prandini.personal.common.QueryUtils;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LancamentoRepositoryCustomImpl implements LancamentoRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Lancamento> findByConta(LancamentoFilter filter) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT l ")
                .append("FROM lancamento AS l ")
                .append("JOIN l.conta AS c ")
                .append("WHERE 1 = 1 ");

        QueryUtils.safeAddParams(params, "conta", filter.getConta(), sb, "AND c.name = :conta ");

        sb.append("ORDER BY l.data , ")
                .append("l.tipoLancamento , ")
                .append("l.categoriaLancamento ");

        Query query = this.entityManager.createQuery(sb.toString());
        params.forEach(query::setParameter);

        return query.getResultList();
    }

    @Override
    public Stream<Lancamento> findStreamByFilter(LancamentoFilter filter) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT l ")
                .append("FROM lancamento AS l ")
                .append("WHERE 1 = 1 ");

        QueryUtils.safeAddParams(params, "id", filter.getId(), sb, "AND l.id = :id ");
        QueryUtils.safeAddParams(params, "dataInicio", filter.getDataInicio(), sb, "AND l.data >= :dataInicio ");
        QueryUtils.safeAddParams(params, "dataFim", filter.getDataFim(), sb, "AND l.data <= :dataFim ");
        QueryUtils.safeAddParams(params, "categoria", filter.getCategoria(), sb, "AND l.categoriaLancamento = :categoria ");
        QueryUtils.safeAddParams(params, "dataFim", filter.getTipo(), sb, "AND l.tipoLancamento = :tipo ");

        sb.append("ORDER BY l.data ASC, ")
                .append("l.valor");

        Query query = this.entityManager.createQuery(sb.toString());
        params.forEach(query::setParameter);

        return query.getResultStream();
    }
}
