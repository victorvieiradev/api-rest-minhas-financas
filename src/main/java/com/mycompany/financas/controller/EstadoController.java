package com.mycompany.financas.controller;

import com.mycompany.financas.model.EstadoDto;
import com.mycompany.financas.model.EstadoModel;
import com.mycompany.financas.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService;
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid EstadoDto estadoDto){
        if (estadoService.existsByUf(estadoDto.getUf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe estado cadastrado com esse UF");
        }
        if (estadoService.existsByNomeEstado(estadoDto.getNomeEstado())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe estado cadastrado com esse nome. ");
        }
        var estadoModel = new EstadoModel();
        BeanUtils.copyProperties(estadoDto, estadoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.salvar(estadoModel));
    }
    @GetMapping
    public ResponseEntity<List<EstadoModel>> exibir(){
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.exibir());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Integer id ){
        Optional<EstadoModel> estadoModelOptional = estadoService.buscarPorId(id);
        if (!estadoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Estado informado não foi encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(estadoModelOptional.get());
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> excluir(@PathVariable(value = "id") Integer id ){
        Optional<EstadoModel> estadoModelOptional = estadoService.buscarPorId(id);
        if (!estadoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Estado não foi encontrado para ser excluido.");
        }
        estadoService.excluir(estadoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso.");
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer id, @RequestBody @Valid EstadoDto estadoDto ){
        Optional<EstadoModel> estadoModelOptional = estadoService.buscarPorId(id);
        if (!estadoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Estado informado para a atualização não foi encontrado.");
        }
        var estadoModel = new EstadoModel();
        BeanUtils.copyProperties(estadoDto, estadoModel);
        estadoModel.setId(estadoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.salvar(estadoModel));
    }
}
