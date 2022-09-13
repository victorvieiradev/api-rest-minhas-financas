package com.mycompany.financas.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "TB_ESTADOS")
public class EstadoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 2)
    private String uf;
    @Column(nullable = false, unique = true, length = 50)
    private String nomeEstado;

}
