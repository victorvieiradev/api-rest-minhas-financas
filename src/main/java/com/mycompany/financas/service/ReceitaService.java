package com.mycompany.financas.service;

import com.mycompany.financas.model.ReceitaModel;
import com.mycompany.financas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;
    public ReceitaModel salvar(ReceitaModel receitaModel){
        return receitaRepository.save(receitaModel);
    }
    public List<ReceitaModel> exibir(){
        return receitaRepository.findAll();
    }
    public Optional<ReceitaModel> buscarPorId(Integer id ){
        return receitaRepository.findById(id);
    }
    public void excluir(ReceitaModel receitaModel ){
        receitaRepository.delete(receitaModel);
    }
}
