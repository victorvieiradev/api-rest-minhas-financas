package com.mycompany.financas.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class EstadoDto {
    @NotBlank
    private String uf;
    @NotBlank
    private String nomeEstado;

}
