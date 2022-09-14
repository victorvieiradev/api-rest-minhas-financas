package com.mycompany.financas.model;

import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Data
public class ReceitaDto {
    //@NotBlank
    private RecebimentoEnum recebimento;
    //@NotBlank(message = "O valor não pode estar em branco.")
    private double valorRecebimento;
    //@NotBlank
    private LocalDate dataDeVencimento;
    private LocalDate dataDeRecebimento;
    private StatusEnum status;
    @ManyToOne
    //@JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioModel usuario;

}
