package com.mycompany.financas.service;

import com.mycompany.financas.model.ContasAPagarModel;
import com.mycompany.financas.model.DadosContaModel;
import com.mycompany.financas.model.StatusEnum;
import com.mycompany.financas.model.TipoEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ContasAPagarFactory {
    public ContasAPagarModel novaConta(DadosContaModel dados ){

        dados.setTipo(this.avaliarTipo(dados.getTipo()));
        dados.setStatus(this.avaliarStatus(dados.getDataDeVencimento(), dados.getStatus()));
        return new ContasAPagarModel(dados.getId(), dados.getNome(), dados.getValor(), dados.getTipo(), dados.getStatus(), dados.getDataDeVencimento(), dados.getDataDePagamento());
    }
    public StatusEnum avaliarStatus(LocalDate dataDeVencimento, StatusEnum status ){
        LocalDate hoje = LocalDate.now();
        if (dataDeVencimento.isBefore(hoje) && status != StatusEnum.PAGO){
            return StatusEnum.VENCIDA;
        }else if (status != StatusEnum.PAGO){
            return StatusEnum.AGUARDANDO;
        }else {
            return StatusEnum.PAGO;
        }
    }
    public TipoEnum avaliarTipo(TipoEnum tipo ){
        if (tipo == null){
            return TipoEnum.OUTROS;
        }
        return tipo;
    }

}
