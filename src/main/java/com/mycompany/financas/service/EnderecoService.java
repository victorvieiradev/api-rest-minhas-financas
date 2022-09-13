package com.mycompany.financas.service;

import com.mycompany.financas.model.EnderecoModel;
import com.mycompany.financas.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Transactional
    public EnderecoModel salvarEndereco(EnderecoModel enderecoModel){
        return enderecoRepository.save(enderecoModel);
    }
    public List<EnderecoModel> exibir(){
        return enderecoRepository.findAll();
    }
    public Optional<EnderecoModel> procurarPorId(Integer id ){
        return enderecoRepository.findById(id);
    }
    @Transactional
    public void excluir(EnderecoModel enderecoModel){
        enderecoRepository.delete(enderecoModel);
    }
}
