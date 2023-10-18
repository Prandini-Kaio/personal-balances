package com.prandini.personal.lancamento.service;

import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.exceptions.LancamentoException;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.service.actions.LancamentoReportAction;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.io.File;

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
            validator.validar(lancamentoInput);
        } catch (LancamentoException e) {
            throw new RuntimeException(e);
        }

        return register.register(lancamentoInput);
    }

    public File getCsvByFilter(LancamentoFilter filter){
        return reporter.getReportCsvByFilter(filter);
    }
}
