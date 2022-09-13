package com.mycompany.financas.controller;

import com.mycompany.financas.model.ExibirUsuario;
import com.mycompany.financas.model.UsuarioDto;
import com.mycompany.financas.model.UsuarioModel;
import com.mycompany.financas.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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
    @GetMapping
    public ResponseEntity<List<ExibirUsuario>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuario());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Integer id){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.buscarPorId(id);
        if (!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> excluir(@PathVariable(value = "id") Integer id ){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.buscarPorId(id);
        if (!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        usuarioService.excluir(usuarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("O usuário foi excluído com sucesso!");
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer id, UsuarioDto usuarioDto ){
        Optional<UsuarioModel> usuarioModelOptional = usuarioService.buscarPorId(id);
        if (!usuarioModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuário que foi informado para ser atualizado não existe.");
        }
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        usuarioModel.setId(usuarioModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.salvarUsuario(usuarioModel));

    }

}
