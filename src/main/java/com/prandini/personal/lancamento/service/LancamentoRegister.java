package com.prandini.personal.lancamento.service;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.service.ContaGetter;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.Parcela;
import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.repository.LancamentoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class LancamentoRegister {

    @Resource
    private LancamentoRepository repository;
    @Resource
    private ContaGetter contaGetter;

    public LancamentoOutput register(LancamentoInput input){
        Conta conta = contaGetter.findByBancoAndConta(input.getBanco(), input.getConta());

        Lancamento lancamento = new Lancamento();

        lancamento.setValorTotal(input.getValor());
        lancamento.setDescricao(input.getDescricao());
        lancamento.setCategoriaLancamento(input.getCategoriaLancamento());
        lancamento.setTipoLancamento(input.getTipoLancamento());
        lancamento.setData(LocalDateTime.now());

        List<Parcela> parcelas = new ArrayList<>();
        for(int i = 1; i <= input.getParcelas(); i++){
            Parcela parcela = new Parcela();
            parcela.setValor(input.getValor().divide(BigDecimal.valueOf(input.getParcelas())));
            //parcela.setLancamento(lancamento);
            parcela.setNumero(i);
            parcela.setDataVencimento(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().plus(i), conta.getCreditCard().getDiaVencimento()));

            parcelas.add(parcela);
        }

        lancamento.setParcelas(parcelas);

        conta.addLancamento(lancamento);

        lancamento.setConta(conta);

        repository.save(lancamento);

        return LancamentoConverter.toOutput(lancamento);
    }
}
