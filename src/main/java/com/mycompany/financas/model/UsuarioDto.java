package com.mycompany.financas.model;

import lombok.Data;


import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class UsuarioDto {
    @NotBlank(message = "O nome não pode ser nulo ou branco.")
    private String nomeUsuario;
    //@NotBlank
    private Date dataNascimento;
    @NotBlank(message = "O email não pode estar em branco.")
    private String email;
    @NotBlank(message = "O CPF não pode estar em branco.")
    private String cpf;
}
