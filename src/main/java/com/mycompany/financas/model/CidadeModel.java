package com.mycompany.financas.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "TB_CIDADES")
public class CidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 100)
    private String nomeCidade;
    @ManyToOne
    private EstadoModel estado;
    @OneToMany
    private List<EnderecoModel> endereco;

}
