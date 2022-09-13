package com.mycompany.financas.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Data
public class ReceitaDto {
    //@NotBlank
    private RecebimentoEnum recebimento;
    //@NotBlank
    private double valorRecebimento;
    //@NotBlank
    private LocalDate dataDeVencimento;
    private LocalDate dataDeRecebimento;
    private StatusEnum status;

}
