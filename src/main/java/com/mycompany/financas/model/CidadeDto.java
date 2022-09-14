package com.mycompany.financas.model;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
@Data
public class CidadeDto {
    @NotBlank(message = "O nome da cidade não pode estar em branco.")
    private String nomeCidade;
    @ManyToOne
    private EstadoModel estado;


}
