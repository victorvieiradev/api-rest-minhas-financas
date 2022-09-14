package com.mycompany.financas.controller;

import com.mycompany.financas.model.ReceitaDto;
import com.mycompany.financas.model.ReceitaModel;
import com.mycompany.financas.model.StatusEnum;
import com.mycompany.financas.service.ReceitaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "receita")
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;
    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid ReceitaDto receitaDto){
        LocalDate hoje = LocalDate.now();
        if (receitaDto.getDataDeVencimento().isBefore(hoje)){
            receitaDto.setStatus(StatusEnum.EM_ATRAZO);
        }else if (hoje.isBefore(receitaDto.getDataDeVencimento())){
            receitaDto.setStatus(StatusEnum.EM_DIA);
        }
        if (receitaDto.getDataDeRecebimento() != null){
            if (receitaDto.getDataDeRecebimento().isBefore(hoje)){
                receitaDto.setStatus(StatusEnum.ADIANTADO);
            }
        }
        var receitaModel = new ReceitaModel();
        BeanUtils.copyProperties(receitaDto, receitaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.salvar(receitaModel));
    }
    @GetMapping
    public ResponseEntity<List<ReceitaModel>> exibir(){
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.exibir());
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") Integer id ){
        Optional<ReceitaModel> receitaModelOptional = receitaService.buscarPorId(id);
        if (!receitaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receita não encontrada ou não registrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(receitaModelOptional.get());
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Integer id, @RequestBody @Valid ReceitaDto receitaDto){
        Optional<ReceitaModel> receitaModelOptional = receitaService.buscarPorId(id);
        if (!receitaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A receita solicitada para ser  atualizada não foi encontrada.");
        }
        var receitaModel = new ReceitaModel();
        BeanUtils.copyProperties(receitaDto, receitaModel);
        receitaModel.setId(receitaModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(receitaService.salvar(receitaModel));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> excluir(@PathVariable(value = "id") Integer id ){
        Optional<ReceitaModel> receitaModelOptional = receitaService.buscarPorId(id);
        if (!receitaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receita não encontrada ou não registrada.");
        }
        receitaService.excluir(receitaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Receita excluida.");
    }
    @GetMapping(path = "/pesquisa")
    public ResponseEntity<List<ReceitaModel>> buscarPorStatus(@RequestParam(value = "status", defaultValue = "", required = true) StatusEnum status ){
    return ResponseEntity.status(HttpStatus.OK).body(receitaService.buscarPorStatus(status));
    }
}
