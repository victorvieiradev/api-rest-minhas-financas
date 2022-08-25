package com.mycompany.financas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contasapagar")
public class ContasAPagarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = "{nome.not.blank}")
    private String nome;
    //@NotBlank(message = "{valor.not.blank}")
    private double valor;
    private TipoEnum tipo;
    private StatusEnum status;
    //@NotBlank(message = "{dataDeVencimento.not.blank}")
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
