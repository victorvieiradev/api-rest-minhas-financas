package com.mycompany.financas.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ReceitaDto {
    private RecebimentoEnum recebimento;
    private double valorRecebimento;
    private LocalDate dataDeVencimento;
    private LocalDate dataDeRecebimento;
    private StatusEnum status;

}
