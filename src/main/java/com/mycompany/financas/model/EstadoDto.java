package com.mycompany.financas.model;

import javax.validation.constraints.NotBlank;

public class EstadoDto {
    @NotBlank
    private String uf;
    @NotBlank
    private String nomeEstado;

}
