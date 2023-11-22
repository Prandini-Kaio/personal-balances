package com.prandini.personal.banco.domain;

import com.prandini.personal.banco.model.banco.BancoOutput;

public class BancoConverter {

    public static BancoOutput toOutput(Banco banco){
        return new BancoOutput(banco.getId(), banco.getName());
    }
}
