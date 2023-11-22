package com.prandini.personal.banco.service.banco;

import com.prandini.personal.banco.domain.BancoConverter;
import com.prandini.personal.banco.model.banco.BancoInput;
import com.prandini.personal.banco.model.banco.BancoOutput;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BancoService {

    @Resource
    private BancoRegister register;

    public BancoOutput register(BancoInput input){
        return BancoConverter.toOutput(register.create(input));
    }
}
