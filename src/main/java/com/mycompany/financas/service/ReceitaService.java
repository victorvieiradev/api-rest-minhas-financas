package com.mycompany.financas.service;

import com.mycompany.financas.model.RecebimentoEnum;
import com.mycompany.financas.model.ReceitaModel;
import com.mycompany.financas.model.StatusEnum;
import com.mycompany.financas.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private ReceitaFactory receitaFactory;
    public ReceitaModel salvar(ReceitaModel receitaModel){
        var receita = receitaFactory.desconto(receitaModel);
        return receitaRepository.save(receita);
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
    public List<ReceitaModel> buscarPorStatus(StatusEnum status ){
        return receitaRepository.findByStatus(status);
    }
    public List<ReceitaModel> buscarPorTipoRecebimento(RecebimentoEnum recebimento ){
        return receitaRepository.findByRecebimento(recebimento);
    }
    public List<ReceitaModel> buscarPorData(LocalDate dataDeVencimento){
        return receitaRepository.findBydataDeVencimento(dataDeVencimento);
    }
}
