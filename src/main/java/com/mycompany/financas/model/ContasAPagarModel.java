package com.mycompany.financas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContasAPagarModel {
    private String nome;
    private double valor;
    private TipoEnum tipo;
    private StatusEnum status;
    private LocalDate dataDeVencimento;
    private LocalDateTime dataDePagamento;

}
