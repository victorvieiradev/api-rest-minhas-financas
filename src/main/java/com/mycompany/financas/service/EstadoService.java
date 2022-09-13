package com.mycompany.financas.service;

import com.mycompany.financas.model.EstadoModel;
import com.mycompany.financas.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;
    public List<EstadoModel> exibir(){
        return estadoRepository.findAll();
    }
    public boolean existsByUf(String uf){
        return estadoRepository.existsByUf(uf);
    }
    public boolean existsByNomeEstado(String estado){
        return estadoRepository.existsByNomeEstado(estado);
    }
    @Transactional
    public EstadoModel salvar(EstadoModel estadoModel){
        return estadoRepository.save(estadoModel);
    }
    public Optional<EstadoModel> buscarPorId(Integer id ){
        return estadoRepository.findById(id);
    }
    @Transactional
    public void excluir(EstadoModel estadoModel){
        estadoRepository.delete(estadoModel);
    }
}
