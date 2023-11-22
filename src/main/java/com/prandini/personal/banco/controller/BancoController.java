package com.prandini.personal.banco.controller;

import com.prandini.personal.banco.model.banco.BancoInput;
import com.prandini.personal.banco.service.banco.BancoService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/banco")
public class BancoController {

    @Resource
    private BancoService service;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid BancoInput input){
        return ResponseEntity.ok().body(service.register(input));
    }
}
