package com.mycompany.financas.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class CidadeDto {
    @NotBlank
    private String nomeCidade;

}
