package com.mycompany.financas.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class EstadoDto {
    @NotBlank(message = "O uf do estado não pode estar em branco.")
    private String uf;
    @NotBlank(message = "O nome do estado não pode estar em branco.")
    private String nomeEstado;

}
