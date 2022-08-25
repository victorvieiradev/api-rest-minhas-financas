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
dados.setTipo(this.avaliarTipo(dados.getTipo()));
dados.setStatus(this.avaliarStatus(dados.getDataDeVencimento()));
        return new ContasAPagarModel(dados.getId(), dados.getNome(), dados.getValor(), dados.getTipo(), dados.getStatus(), dados.getDataDeVencimento(), dados.getDataDePagamento());
    }
    public StatusEnum avaliarStatus(LocalDate dataDeVencimento){
        LocalDate hoje = LocalDate.now();
        if (dataDeVencimento.isBefore(hoje)){
            return StatusEnum.VENCIDA;
        }else {
            return StatusEnum.AGUARDANDO;
        }
    }
    public TipoEnum avaliarTipo(TipoEnum tipo ){
        if (tipo == null){
            return TipoEnum.OUTROS;
        }
        return null;
    }
}
