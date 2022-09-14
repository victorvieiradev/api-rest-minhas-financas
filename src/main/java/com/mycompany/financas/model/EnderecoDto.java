package com.mycompany.financas.model;

import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Data
public class EnderecoDto {
    @NotBlank(message = "O logradouro não pode estar em branco.")
    private String logradouro;
    @NotBlank(message = "O bairro não pode estar em branco.")
    private String bairro;
    @NotBlank(message = "O CEP não pode estar em branco.")
    private String cep;
    @NotBlank(message = "Ponto de referência não pode estar em branco.")
    private String pontoReferencia;
    @ManyToOne
    private CidadeModel cidade;
    @ManyToOne
    private UsuarioModel usuario;

}
