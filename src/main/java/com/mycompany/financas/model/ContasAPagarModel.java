package com.mycompany.financas.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contasapagar")
public class ContasAPagarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double valor;
    private TipoEnum tipo;
    private StatusEnum status;
    private LocalDate dataDeVencimento;
    private LocalDateTime dataDePagamento;
    public ContasAPagarModel(Long id, String nome, double valor, TipoEnum tipo, LocalDate dataDeVencimento) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
        this.dataDeVencimento = dataDeVencimento;
    }
}
