package com.prandini.personal.lancamento.service.actions;

import com.prandini.personal.common.ExportadorService;
import com.prandini.personal.common.FileService;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.service.LancamentoGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.io.File;
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
        Stream<Lancamento> stream = lancamentoGetter.getStreamByFilter(filter);

        String filename = fileService.getFullFilename("relatorio-lancamento");
        ExportadorService.exportarDadosParaCSVMap(stream, colunasRelatorio(), filename);
        return new File(filename);
    }

    public LinkedHashMap<String, Function<Lancamento, String>> colunasRelatorio(){

        LinkedHashMap<String, Function<Lancamento, String>> getters = new LinkedHashMap<>();
        getters.put("Nome Da Coluna", l -> l.getCategoriaLancamento().toString());

        return getters;
    }
}
