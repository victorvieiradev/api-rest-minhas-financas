package com.mycompany.financas.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class DadosContaModel {
    private Long id;
    private String nome;
    private double valor;
    private TipoEnum tipo;
    private StatusEnum status;
    private LocalDate dataDeVencimento;
    private LocalDateTime dataDePagamento;


}
