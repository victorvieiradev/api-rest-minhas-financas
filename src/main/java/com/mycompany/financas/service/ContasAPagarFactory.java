package com.mycompany.financas.service;

import com.mycompany.financas.model.ContasAPagarModel;
import com.mycompany.financas.model.DadosContaModel;
import com.mycompany.financas.model.StatusEnum;
import com.mycompany.financas.model.TipoEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContasAPagarFactory {
    public ContasAPagarModel novaConta(DadosContaModel dados ){
        if (dados.getTipo() == null){
            dados.setTipo(TipoEnum.OUTROS);
        }
        LocalDate hoje = LocalDate.now();
        if (dados.getDataDeVencimento().isBefore(hoje)){
            dados.setStatus(StatusEnum.VENCIDA);
        }else {
            dados.setStatus(StatusEnum.AGUARDANDO);
        }
        return new ContasAPagarModel(dados.getId(), dados.getNome(), dados.getValor(), dados.getTipo(), dados.getStatus(), dados.getDataDeVencimento(), dados.getDataDePagamento());
    }
}
