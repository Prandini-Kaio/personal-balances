package com.prandini.personal.lancamento.service.actions;

import com.prandini.personal.common.ExportadorService;
import com.prandini.personal.common.FileService;
import com.prandini.personal.common.LocalDateConverter;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.service.LancamentoGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
@CommonsLog
public class LancamentoReportAction {
    @Resource
    private LancamentoGetter lancamentoGetter;

    @Resource
    private FileService fileService;

    public File getReportCsvByFilter(LancamentoFilter filter){
        Stream<Lancamento> stream = lancamentoGetter.findByFilter(filter);

        String filename = fileService.getFullFilename(String.format("lancamentos-%s-%s", LocalDateConverter.toDateNumbersOnly(LocalDateTime.now()), LocalDateConverter.toTimeNumbersOnly(LocalDateTime.now())));
        ExportadorService.exportarDadosParaCSVMap(stream, colunasRelatorio(), filename);
        return new File(filename);
    }

    public LinkedHashMap<String, Function<Lancamento, String>> colunasRelatorio(){

        LinkedHashMap<String, Function<Lancamento, String>> getters = new LinkedHashMap<>();
        getters.put("ID", l -> l.getId().toString());
        getters.put("Data de registro", l -> LocalDateConverter.toBrazilianDateTimeString(l.getData()));
        getters.put("Valor", l -> "R$" + l.getValor().toString());
        getters.put("Tipo", l -> l.getTipoLancamento().toString());
        getters.put("Categoria", l -> l.getCategoriaLancamento().toString());
        return getters;
    }
}
