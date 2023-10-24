package com.prandini.personal.lancamento.service;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.exceptions.LancamentoException;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.model.dto.CostOfMonthDTO;
import com.prandini.personal.lancamento.service.actions.LancamentoReportAction;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

@Component
public class LancamentoService {

    @Resource
    private LancamentoGetter getter;
    @Resource
    private LancamentoRegister register;
    @Resource
    private LancamentoUpdater updater;

    @Resource
    private LancamentoValidator validator;

    @Resource
    private LancamentoReportAction reporter;

    public Page<LancamentoOutput> getPageOfContas(Pageable pageable){
        Page<Lancamento> contas = this.getter.pageAll(pageable);
        return LancamentoConverter.toOutput(contas);
    }

    public LancamentoOutput registerLancamento(LancamentoInput lancamentoInput){
        try {
            validator.execute(lancamentoInput);
        } catch (LancamentoException e) {
            throw new RuntimeException(e);
        }

        return register.register(lancamentoInput);
    }

    public LancamentoOutput update(LancamentoInput input){
        return LancamentoConverter.toOutput(updater.update(input));
    }

    public LancamentoOutput desactive(Long id){
        return LancamentoConverter.toOutput(updater.desactive(id));
    }

    public List<LancamentoOutput> getByConta(String conta){
        return LancamentoConverter.toOutput(this.getter.byConta(conta));
    }

    public Stream<LancamentoOutput> findStreamByFilter(LancamentoFilter filter){
        return LancamentoConverter.toOutput(getter.byFilter(filter));
    }

    public File byFilter(LancamentoFilter filter){
        return reporter.csvByFilter(filter);
    }

    public List<CostOfMonthDTO> getCostOfMonth(Integer mes){
        return getter.costByMes(mes);
    }
}
