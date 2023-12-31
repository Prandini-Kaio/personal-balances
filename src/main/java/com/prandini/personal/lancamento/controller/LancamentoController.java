package com.prandini.personal.lancamento.controller;

import com.prandini.personal.common.MediaTypeUtil;
import com.prandini.personal.lancamento.domain.filter.LancamentoFilter;
import com.prandini.personal.lancamento.enums.CategoriaLancamento;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.model.dto.CostOfMonthDTO;
import com.prandini.personal.lancamento.model.dto.PayParcelasInput;
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
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {

    @Resource
    private LancamentoService service;

    @GetMapping
    public ResponseEntity<Page<LancamentoOutput>> getLancamentos(@PageableDefault(sort = "data")Pageable pageable){
        return ResponseEntity.ok(this.service.getPageOfContas(pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<Stream<LancamentoOutput>> getByFilter(@RequestBody @Valid LancamentoFilter filter){
        return ResponseEntity.ok(this.service.byFilter(filter));
    }

    @GetMapping("/{conta}")
    public ResponseEntity<List<LancamentoOutput>> getByFilter(@PathVariable("conta") String conta, @PathVariable("banco") String banco){
        return ResponseEntity.ok(this.service.getByConta(conta, banco));
    }

    @GetMapping("/cost")
    public ResponseEntity<List<CostOfMonthDTO>> getCostByMes(@RequestParam(required = false) Integer mes){
        return ResponseEntity.ok(service.getCostOfMonth(mes));
    }

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid LancamentoInput conta){
        return ResponseEntity.ok(this.service.register(conta));
    }

    @PostMapping("/pay")
    public ResponseEntity payParcela(@RequestBody @Valid PayParcelasInput input){
        return ResponseEntity.ok().body(service.payParcelas(input));
    }

    @PutMapping
    public ResponseEntity update(LancamentoInput input){
        return ResponseEntity.ok().body(service.update(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.desactive(id));
    }


    @GetMapping("/exportar-csv")
    public ResponseEntity<InputStreamResource> getCsv(@RequestBody @Valid LancamentoFilter filter){
        File file = this.service.fileByFilter(filter);

        try {
            InputStreamResource body = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaTypeUtil.frowFile(file)).body(body);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/byCategoria")
    public ResponseEntity<List<LancamentoOutput>> byCategoria(@RequestParam(required = false)CategoriaLancamento categoriaLancamento){
        return ResponseEntity.ok().body(service.byCategoria(categoriaLancamento));
    }
}
