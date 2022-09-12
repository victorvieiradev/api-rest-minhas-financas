package com.mycompany.financas.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
public class UsuarioDto {
    @NotBlank
    private String nomeUsuario;
    @NotBlank
    private Date dataNascimento;
    @NotBlank
    private String email;
    @NotBlank
    private String cpf;

}
