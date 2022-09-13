package com.mycompany.financas.controller;

import com.mycompany.financas.model.CidadeDto;
import com.mycompany.financas.model.CidadeModel;
import com.mycompany.financas.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "cidade")
public class CidadeController {
    @Autowired
    private CidadeService cidadeService;
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CidadeDto cidadeDto){
        if (cidadeService.existsByNomeCidade(cidadeDto.getNomeCidade())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A cidade informada já está cadastrada.");
        }
        var cidadeModel = new CidadeModel();
        BeanUtils.copyProperties(cidadeDto, cidadeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.salvar(cidadeModel));
    }
    @GetMapping
    public ResponseEntity<List<CidadeModel>> exibir(){
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.exibir());
    }
    @GetMapping(path = "id")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Integer id ){
        Optional<CidadeModel> cidadeModelOptional = cidadeService.buscarPorId(id);
        if (!cidadeModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A cidade informada não foi encontrada ou não está cadastrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cidadeModelOptional.get());
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> excluir(@PathVariable(value = "id") Integer id ){
        Optional<CidadeModel> cidadeModelOptional = cidadeService.buscarPorId(id);
        if (!cidadeModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A cidade não foi encontrada.");
        }
        cidadeService.excluir(cidadeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cidade excluida ");
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer id, @RequestBody @Valid CidadeDto cidadeDto){
        Optional<CidadeModel> cidadeModelOptional = cidadeService.buscarPorId(id);
        if (!cidadeModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe cidade com o id informado para ser atualizado.");
        }
        var cidadeModel = new CidadeModel();
        BeanUtils.copyProperties(cidadeDto, cidadeModel);
        cidadeModel.setId(cidadeModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.salvar(cidadeModel));
    }
}
