package com.mycompany.financas.controller;

import com.mycompany.financas.exception.ContaNaoEncontradaException;
import com.mycompany.financas.model.*;

import com.mycompany.financas.service.ContasAPagarService;
import com.mycompany.financas.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ContasAPagarController {
    @Autowired
    private ContasAPagarService contasAPagarService;
    @PostMapping(path = "/conta")
    public ResponseEntity<ContasAPagarModel> cadastrarNovaConta(@RequestBody DadosContaModel dados ){
        if (dados.getDataDeVencimento() == null){
            throw new ObjectNotFoundException("A data não pode estar errada ou vazia.");
        }
        if (dados.getNome() == null){
            throw new ObjectNotFoundException("O nome não pode estar errado ou vazio.");
        }
        if (dados.getValor() == 0){
            throw new ObjectNotFoundException("O valor não pode estar errado ou vazio.");
        }

        ContasAPagarModel conta = contasAPagarService.cadastrarNovaConta(dados);
        return new ResponseEntity<>(conta, HttpStatus.CREATED);
    }
    @PutMapping(path = "/conta/{id}")
    public ResponseEntity editarConta(@PathVariable Long id, @RequestBody DadosContaModel dados ){

        try{
            ContasAPagarModel conta = contasAPagarService.editarConta(id, dados);
            return new ResponseEntity<>(conta, HttpStatus.CREATED);
        }catch (ContaNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping(path = "/listar")
    public ResponseEntity<List<ExibirConta>> listarContas(){
        return ResponseEntity.ok(contasAPagarService.listarContas());
    }
    @GetMapping(path = "/conta")
    public ResponseEntity<List<ContasAPagarModel>>buscarContasDetalhadas(){
        return ResponseEntity.ok(contasAPagarService.buscarTodos());
    }
    @GetMapping(path = "/conta/{id}")
    public ResponseEntity<Optional<ContasAPagarModel>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(contasAPagarService.buscarPorId(id));
    }
    @GetMapping(path = "/conta/search")
    public ResponseEntity<List<ContasAPagarModel>> buscarPorStatus(@RequestParam(value = "status", defaultValue = "", required = true) StatusEnum status ){
        return ResponseEntity.ok(contasAPagarService.buscarPorStatus(status));
    }
    @GetMapping(path = "/conta/search/tipo")
    public ResponseEntity<List<ContasAPagarModel>> buscarPorTipo(@RequestParam(value = "tipo", defaultValue = "", required = true) TipoEnum tipo ){
        return ResponseEntity.ok(contasAPagarService.buscarPorTipo(tipo));
    }
    @DeleteMapping(path = "/conta/{id}")
    public void excluirContaPorId(@PathVariable Long id){
        contasAPagarService.excluirPorId(id);
    }
}
