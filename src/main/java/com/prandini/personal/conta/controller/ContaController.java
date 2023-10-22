package com.prandini.personal.conta.controller;

import com.prandini.personal.conta.domain.converter.ContaConverter;
import com.prandini.personal.conta.model.ContaInput;
import com.prandini.personal.conta.model.ContaOutput;
import com.prandini.personal.conta.repository.ContaRepository;
import com.prandini.personal.conta.service.ContaService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Resource
    private ContaService service;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid ContaInput input){
        return ResponseEntity.ok(this.service.register(input));
    }

    @GetMapping("/{id}")
    public ResponseEntity getContaById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getIfExists(id));
    }

    @GetMapping
    public ResponseEntity getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PutMapping
    public ResponseEntity update(ContaInput input){
        return ResponseEntity.ok(service.update(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.desactive(id));
    }
}
