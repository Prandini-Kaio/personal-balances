package com.prandini.personal.lancamento.repository;

import com.prandini.personal.common.QueryUtils;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.model.dto.CostOfMonthDTO;
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
    public List<Lancamento> byConta(String conta) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT l ")
                .append("FROM lancamento AS l ")
                .append("JOIN l.conta AS c ")
                .append("WHERE 1 = 1 ");

        QueryUtils.safeAddParams(params, "conta", conta, sb, "AND c.name = :conta ");

        sb.append("ORDER BY l.data , ")
                .append("l.tipoLancamento , ")
                .append("l.categoriaLancamento ");

        Query query = this.entityManager.createQuery(sb.toString());
        params.forEach(query::setParameter);

        return query.getResultList();
    }

    @Override
    public Stream<Lancamento> byFilter(LancamentoFilter filter) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT l ")
                .append("FROM lancamento AS l ")
                .append("JOIN l.conta AS c ")
                .append("WHERE 1 = 1 ");

        QueryUtils.safeAddParams(params, "id", filter.getId(), sb, "AND l.id = :id ");
        QueryUtils.safeAddParams(params, "conta", filter.getConta(), sb, "AND c.name = :conta ");
        QueryUtils.safeAddParams(params, "dataInicio", filter.getDataInicio(), sb, "AND l.data >= :dataInicio ");
        QueryUtils.safeAddParams(params, "dataFim", filter.getDataFim(), sb, "AND l.data <= :dataFim ");
        QueryUtils.safeAddParams(params, "categoria", filter.getCategoria(), sb, "AND l.categoriaLancamento = :categoria ");
        QueryUtils.safeAddParams(params, "tipo", filter.getTipo(), sb, "AND l.tipoLancamento = :tipo ");

        sb.append("ORDER BY c.name ASC, ")
                .append("l.tipoLancamento, ")
                .append("l.data ASC ");

        Query query = this.entityManager.createQuery(sb.toString());
        params.forEach(query::setParameter);

        return query.getResultStream();
    }

    @Override
    public List<Object[]> byMes(Integer mes) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT ")
                .append("MONTH(l.data) AS mes, ")
                .append("SUM(l.valor) AS total, ")
                .append("AVG(l.valor) AS media ")
                .append("FROM lancamento AS l ")
                .append("WHERE 1 = 1 ");

        QueryUtils.safeAddParams(params, "mes", mes, sb, "AND MONTH(l.data) = :mes ");

        sb.append("GROUP BY ")
                .append("MONTH(l.data) ")
                .append("ORDER BY MONTH(l.data) ");

        Query query = this.entityManager.createQuery(sb.toString());
        params.forEach(query::setParameter);

        return query.getResultList();
    }

    @Override
    public List<Lancamento> byBancoAndConta(String conta, String banco) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT l ")
                .append("FROM lancamento AS l ")
                .append("JOIN l.conta c ")
                .append("JOIN c.banco b ")
                .append("WHERE 1 = 1 ");

        QueryUtils.safeAddParams(params, "conta", conta, sb, "AND c.name = :conta ");
        QueryUtils.safeAddParams(params, "banco", banco, sb, "AND b.name = :banco ");

        Query query = this.entityManager.createQuery(sb.toString());
        params.forEach(query::setParameter);

        return query.getResultList();
    }
}
