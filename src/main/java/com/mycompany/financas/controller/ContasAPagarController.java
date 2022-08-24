package com.mycompany.financas.controller;

import com.mycompany.financas.model.ContasAPagarModel;
import com.mycompany.financas.service.ContasAPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContasAPagarController {
    @Autowired
    private ContasAPagarService contasAPagarService;
    @GetMapping(path = "/detalhar/contas")
    public ResponseEntity<List<ContasAPagarModel>>buscarContasDetalhadas(){
        return ResponseEntity.ok(contasAPagarService.buscarTodos());
    }
    @GetMapping(path = "/buscar/conta/{id}")
    public ResponseEntity<Optional<ContasAPagarModel>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(contasAPagarService.buscarPorId(id));
    }
    @GetMapping(path = "/pesquisarstatus/conta")
    public ResponseEntity<List<ContasAPagarModel>> buscarPorStatus(@RequestParam(value = "status", defaultValue = "", required = true) String status){
        return ResponseEntity.ok(contasAPagarService.buscarPorStatus(status));
    }
    @GetMapping(path = "/pesquisartipo/conta")
    public ResponseEntity<List<ContasAPagarModel>> buscarPorTipo(@RequestParam(value = "tipo", defaultValue = "", required = true) String tipo){
        return ResponseEntity.ok(contasAPagarService.buscarPorTipo(tipo));
    }
    @DeleteMapping(path = "/excluir/conta/{id}")
    public void excluirContaPorId(@PathVariable Long id){
        contasAPagarService.excluirPorId(id);
    }
}
