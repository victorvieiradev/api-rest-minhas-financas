package com.mycompany.financas.service;

import com.mycompany.financas.exception.ContaNaoEncontradaException;
import com.mycompany.financas.model.ContasAPagarModel;
import com.mycompany.financas.model.DadosContaModel;
import com.mycompany.financas.model.StatusEnum;
import com.mycompany.financas.model.TipoEnum;
import com.mycompany.financas.repository.ContasAPagarRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContasAPagarService {
    @Autowired
    private ContasAPagarRepository contasAPagarRepository;
    @Autowired
    private ContasAPagarFactory contasAPagarFactory;
    public ContasAPagarModel cadastrarNovaConta(DadosContaModel dados ){
        ContasAPagarModel conta = contasAPagarFactory.novaConta(dados);
        return contasAPagarRepository.save(conta);
    }
    public ContasAPagarModel editarConta(Long id,  DadosContaModel dados ) throws ContaNaoEncontradaException{
        //ContasAPagarModel conta = contasAPagarFactory.novaConta(dados);
        Optional<ContasAPagarModel>  contaBuscada = this.buscarPorId(id);
        if (contaBuscada.isPresent()){
            ContasAPagarModel conta = contaBuscada.get();
            conta.setNome(dados.getNome());
            conta.setValor(dados.getValor());
            conta.setTipo(contasAPagarFactory.avaliarTipo(dados.getTipo()));
            conta.setStatus(contasAPagarFactory.avaliarStatus(dados.getDataDeVencimento()));
            conta.setDataDePagamento(contasAPagarFactory.avaliarPagamento(dados.getStatus()));
            conta.setDataDeVencimento(dados.getDataDeVencimento());
            return contasAPagarRepository.save(conta);
        }
        throw new ContaNaoEncontradaException("Não encontrei a conta solicitada.");
    }
    public List<ContasAPagarModel>buscarTodos(){
        return contasAPagarRepository.findAll();
    }
    public Optional<ContasAPagarModel>buscarPorId(Long id){
        return contasAPagarRepository.findById(id);
    }
    public List<ContasAPagarModel>buscarPorStatus(StatusEnum status){
        return contasAPagarRepository.findByStatus(status);
    }
    public List<ContasAPagarModel> buscarPorTipo(TipoEnum tipo){
        return contasAPagarRepository.findByTipo(tipo);
    }
    public void excluirPorId(Long id){
        contasAPagarRepository.deleteById(id);
    }
}
