package com.mycompany.financas.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;
@Data
@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String nomeUsuario;
    @Column(nullable = false)
    private Date dataNascimento;
    @Column(nullable = false, unique = true, length = 100)
    @Email
    private String email;
    @Column(nullable = false, unique = true, length = 11)
    @CPF
    private String cpf;



}
