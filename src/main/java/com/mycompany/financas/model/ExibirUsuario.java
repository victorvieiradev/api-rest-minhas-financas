package com.mycompany.financas.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class ExibirUsuario implements Serializable {
    private Integer id;
    private String nomeUsuario;
    private String dataNascimento;
    private String email;
    public ExibirUsuario(UsuarioModel usuarioModel){
        this.id = usuarioModel.getId();
        this.nomeUsuario = usuarioModel.getNomeUsuario();
        this.dataNascimento = usuarioModel.getDataNascimento().toString();
        this.email = usuarioModel.getEmail();
    }
    public static List<ExibirUsuario> convert(List<UsuarioModel> usuario){
        return usuario.stream().map(ExibirUsuario::new).collect(Collectors.toList());
    }

}
