package com.mycompany.financas.service;

import com.mycompany.financas.model.ContasAPagarModel;
import com.mycompany.financas.repository.ContasAPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContasAPagarService {
    @Autowired
    private ContasAPagarRepository contasAPagarRepository;

    public List<ContasAPagarModel>buscarTodos(){
        return contasAPagarRepository.findAll();
    }
    public Optional<ContasAPagarModel>buscarPorId(Long id){
        return contasAPagarRepository.findById(id);
    }
    public List<ContasAPagarModel>buscarPorStatus(String status){
        return contasAPagarRepository.findByStatusIgnoreCase(status);
    }
    public List<ContasAPagarModel> buscarPorTipo(String tipo){
        return contasAPagarRepository.findByTipoIgnoreCase(tipo);
    }
    public void excluirPorId(Long id){
        contasAPagarRepository.deleteById(id);
    }
}
