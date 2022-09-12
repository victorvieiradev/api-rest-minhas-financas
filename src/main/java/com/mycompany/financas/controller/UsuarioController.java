package com.mycompany.financas.controller;

import com.mycompany.financas.model.UsuarioDto;
import com.mycompany.financas.model.UsuarioModel;
import com.mycompany.financas.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<Object> salvarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        if (usuarioService.existsByEmail(usuarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: o email informado já está cadastrado.");
        }
        if (usuarioService.existsByCpf(usuarioDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: o CPF informado já está cadastrado.");
        }
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioModel));
    }
}
