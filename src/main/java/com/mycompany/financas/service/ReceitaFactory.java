package com.mycompany.financas.service;

import com.mycompany.financas.model.RecebimentoEnum;
import com.mycompany.financas.model.ReceitaModel;
import com.mycompany.financas.model.StatusEnum;


public class ReceitaFactory {
    public ReceitaModel desconto(ReceitaModel receitaModel){
        if (receitaModel.getRecebimento() == RecebimentoEnum.ALUGUEL){
            if (receitaModel.getStatus() == StatusEnum.EM_ATRAZO){
                double multa = (3.5 * receitaModel.getValorRecebimento())/100;
                double novoValor = receitaModel.getValorRecebimento()+multa;
                receitaModel.setValorRecebimento(novoValor);
                return receitaModel;
            }
            if (receitaModel.getStatus()== StatusEnum.ADIANTADO){
                double desconto = (5 * receitaModel.getValorRecebimento())/100;
                double novoValor = receitaModel.getValorRecebimento()-desconto;
                receitaModel.setValorRecebimento(novoValor);
                return receitaModel;
            }
        }
        return receitaModel;
    }
}
