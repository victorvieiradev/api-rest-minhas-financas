package com.mycompany.financas.controller;

import com.mycompany.financas.model.EnderecoDto;
import com.mycompany.financas.model.EnderecoModel;
import com.mycompany.financas.service.EnderecoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid EnderecoDto enderecoDto){
        var enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.salvarEndereco(enderecoModel));
    }
    @GetMapping
    public ResponseEntity<List<EnderecoModel>> exibir(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.exibir());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Integer id ){
        Optional<EnderecoModel> enderecoModelOptional = enderecoService.procurarPorId(id);
        if (!enderecoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereço solicitado não foi encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecoModelOptional.get());
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> excluir(@PathVariable(value = "id") Integer id ){
        Optional<EnderecoModel> enderecoModelOptional = enderecoService.procurarPorId(id);
        if (!enderecoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereço não existe ou não foi cadastrado.");
        }
        enderecoService.excluir(enderecoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("O Endereço foi excluído.");
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer id, @RequestBody @Valid EnderecoDto enderecoDto){
        Optional<EnderecoModel> enderecoModelOptional = enderecoService.procurarPorId(id);
        if (!enderecoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereço solicitado para a atualização não existe.");
        }
        var enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(enderecoDto, enderecoModel);
        enderecoModel.setId(enderecoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.salvarEndereco(enderecoModel));
    }
}
