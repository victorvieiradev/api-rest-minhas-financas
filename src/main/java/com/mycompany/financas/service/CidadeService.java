package com.mycompany.financas.service;

import com.mycompany.financas.model.CidadeModel;
import com.mycompany.financas.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;
    public List<CidadeModel> exibir(){
        return cidadeRepository.findAll();
    }
    @Transactional
    public CidadeModel salvar(CidadeModel cidadeModel){
        return cidadeRepository.save(cidadeModel);
    }
    public boolean existsByNomeCidade(String cidade){
        return cidadeRepository.existsBynomeCidade(cidade);
    }
    public Optional<CidadeModel> buscarPorId(Integer id ){
        return cidadeRepository.findById(id);
    }
    @Transactional
    public void excluir(CidadeModel cidadeModel){
        cidadeRepository.delete(cidadeModel);
    }
}
