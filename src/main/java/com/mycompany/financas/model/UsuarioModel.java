package com.mycompany.financas.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String nomeUsuario;
    @Column(nullable = false)
    @DateTimeFormat
    private Date dataNascimento;
    @Column(nullable = false, unique = true, length = 100)
    @Email
    private String email;
    @Column(nullable = false, unique = true, length = 11)
    @CPF(message = "Informe um CPF válido.")
    private String cpf;
    @OneToMany
    private List<EnderecoModel> endereco;
    @OneToMany
    private List<ReceitaModel> receita;
}
