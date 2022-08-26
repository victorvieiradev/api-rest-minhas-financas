package com.mycompany.financas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
public class ExibirConta {
    private Long id;
    private String nome;
    private double valor;
    private String status;
    public ExibirConta(ContasAPagarModel conta ){
        this.id = conta.getId();
        this.nome = conta.getNome();
        this.valor = conta.getValor();
        this.status = conta.getStatus().toString();
    }
    public static List<ExibirConta> convert(List<ContasAPagarModel> contas ){
        return contas.stream().map(ExibirConta::new).collect(Collectors.toList());
    }
}
