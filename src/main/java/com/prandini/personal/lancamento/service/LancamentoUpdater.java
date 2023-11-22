package com.prandini.personal.lancamento.service;

import com.prandini.personal.banco.service.ContaGetter;
import com.prandini.personal.lancamento.domain.Lancamento;
import com.prandini.personal.lancamento.domain.Parcela;
import com.prandini.personal.lancamento.model.dto.PayParcelasInput;
import com.prandini.personal.lancamento.repository.LancamentoRepository;
import com.prandini.personal.lancamento.model.LancamentoInput;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class LancamentoUpdater {

    @Resource
    private LancamentoRepository repository;

    @Resource
    private ContaGetter contaGetter;

    public Lancamento update(LancamentoInput input){
        Lancamento lancamento = repository.getReferenceById(input.getId());

        lancamento.setConta(contaGetter.findByBancoAndConta(input.getBanco(), input.getConta()));
        lancamento.setData(LocalDateTime.now());
        lancamento.setDescricao(input.getDescricao());
        lancamento.setTipoLancamento(input.getTipoLancamento());
        lancamento.setCategoriaLancamento(input.getCategoriaLancamento());

        return lancamento;
    }

    public Lancamento payParcelas(PayParcelasInput input){
        Lancamento lancamento = repository.byId(input.getLancamentoID());

        List<Parcela> attParcelas = new ArrayList<>();

        BigDecimal valorPagamento = input.getValor();

        for(var p : lancamento.getParcelas()){

            if(Objects.equals(valorPagamento, BigDecimal.ZERO)) break;

            if(!p.getPayed()){
                if(p.getRemainingValue().compareTo(input.getValor()) >= 0){
                    valorPagamento = valorPagamento.subtract(p.getValor());
                    p.setPayedValue(p.getValor());
                    p.setRemainingValue(BigDecimal.ZERO);
                    p.setPayed(true);
                }


            }

            attParcelas.add(p);
        }

        // Seta o valor pago do lancamento
        lancamento.setValorTotalPago(lancamento.getValorTotalPago().add(input.getValor()));
        // Devolve o limite ao cartao
        lancamento.getConta().getCreditCard().setLimiteUtilizado(lancamento.getConta().getCreditCard().getLimiteUtilizado().subtract(input.getValor()));

        lancamento.setParcelas(attParcelas);
        return repository.save(lancamento);
    }

    public Lancamento desactive(Long id){
        Lancamento lancamento = repository.getReferenceById(id);
        lancamento.setAtiva(false);
        return lancamento;
    }
}
