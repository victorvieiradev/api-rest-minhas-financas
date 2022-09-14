package com.mycompany.financas.model;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Data
public class EnderecoDto {
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cep;
    @NotBlank
    private String pontoReferencia;
    @ManyToOne
    private CidadeModel cidade;
    @ManyToOne
    private UsuarioModel usuario;

}
