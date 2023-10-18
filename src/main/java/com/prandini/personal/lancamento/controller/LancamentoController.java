package com.prandini.personal.lancamento.controller;

import com.prandini.personal.common.MediaTypeUtil;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.service.LancamentoService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {

    @Resource
    private LancamentoService service;

    @GetMapping
    public ResponseEntity<Page<LancamentoOutput>> getLancamentos(@PageableDefault(sort = "data")Pageable pageable){
        return ResponseEntity.ok(this.service.getPageOfContas(pageable));
    }

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid LancamentoInput conta){
        return ResponseEntity.ok(this.service.registerLancamento(conta));
    }

    @GetMapping("/exportar-csv")
    public ResponseEntity<InputStreamResource> getCsv(LancamentoFilter filter){
        File file = this.service.getCsvByFilter(filter);
        try {
            InputStreamResource body = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaTypeUtil.frowFile(file)).body(body);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
