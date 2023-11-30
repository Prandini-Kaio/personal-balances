package com.prandini.personal.lancamento.service;

import com.prandini.personal.banco.domain.Conta;
import com.prandini.personal.banco.service.ContaGetter;
import com.prandini.personal.common.LocalDateConverter;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.Parcela;
import com.prandini.personal.lancamento.domain.converter.LancamentoConverter;
import com.prandini.personal.lancamento.model.LancamentoInput;
import com.prandini.personal.lancamento.model.LancamentoOutput;
import com.prandini.personal.lancamento.repository.LancamentoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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


        List<Parcela> parcelas = new ArrayList<>();
        for(int i = 1; i <= input.getParcelas(); i++){

            parcelas.add(Parcela
                    .builder()
                        .valor(input.getValor().divide(BigDecimal.valueOf(input.getParcelas())))
                        .payedValue(BigDecimal.ZERO)
                        .remainingValue(input.getValor().divide(BigDecimal.valueOf(input.getParcelas())))
                        .numero(i)
                        .dataVencimento(LocalDateConverter.getVencimento(i, conta.getCreditCard().getDiaVencimento()))
                        .payed(false)
                    .build());
        }

        Lancamento lancamento = Lancamento.builder()
                        .valorTotal(input.getValor())
                        .valorTotalPago(BigDecimal.ZERO)
                        .data(LocalDateTime.now())
                        .descricao(input.getDescricao())
                        .categoriaLancamento(input.getCategoriaLancamento())
                        .tipoLancamento(input.getTipoLancamento())
                        .ativa(true)
                        .parcelas(parcelas)
                        .conta(conta)
                .build();

        conta.addLancamento(lancamento);

        repository.save(lancamento);

        return LancamentoConverter.toOutput(lancamento);
    }
}
