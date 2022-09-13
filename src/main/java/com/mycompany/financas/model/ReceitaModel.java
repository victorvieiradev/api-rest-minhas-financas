package com.mycompany.financas.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "TB_RECEITAS")
public class ReceitaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@Column(nullable = false)
    private RecebimentoEnum recebimento;
    //@Column(nullable = false)
    private double valorRecebimento;
    //@Column(nullable = false)
    private LocalDate dataDeVencimento;
    private LocalDate dataDeRecebimento;
    private StatusEnum status;


}
