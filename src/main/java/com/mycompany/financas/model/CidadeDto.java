package com.mycompany.financas.model;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
@Data
public class CidadeDto {
    @NotBlank
    private String nomeCidade;
    @ManyToOne
    private EstadoModel estado;


}
