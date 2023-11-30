package com.prandini.personal.lancamento.service;

import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.model.dto.CostOfMonthDTO;
import com.prandini.personal.lancamento.model.dto.PayParcelasInput;
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
        return LancamentoConverter.toOutput(this.getter.pageAll(pageable));
    }

    public LancamentoOutput register(LancamentoInput input){
        validator.executeLancamento(input);
        return register.register(input);
    }

    public LancamentoOutput update(LancamentoInput input){
        return LancamentoConverter.toOutput(updater.update(input));
    }

    public LancamentoOutput payParcelas(PayParcelasInput input){
        validator.validaPagamento(input);
        return LancamentoConverter.toOutput(updater.payParcelas(input));
    }

    public LancamentoOutput desactive(Long id){
        return LancamentoConverter.toOutput(updater.desactive(id));
    }

    public List<LancamentoOutput> getByConta(String conta, String banco){
        return LancamentoConverter.toOutput(this.getter.byContaAndBanco(conta, banco));
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
