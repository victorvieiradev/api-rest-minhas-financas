package com.mycompany.financas.service;

import com.mycompany.financas.exception.ContaNaoEncontradaException;
import com.mycompany.financas.model.*;
import com.mycompany.financas.repository.ContasAPagarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
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
        Optional<ContasAPagarModel>  contaBuscada = this.buscarPorId(id);
        if (contaBuscada.isPresent()){
            ContasAPagarModel conta = contaBuscada.get();
            if (dados.getNome() == null){
                conta.setNome(conta.getNome());
            }else {
                conta.setNome(dados.getNome());
            }
            if (dados.getValor() == 0){
                conta.setValor(conta.getValor());
            }else {
                conta.setValor(dados.getValor());
            }
            if (dados.getTipo() == null){
                conta.setTipo(conta.getTipo());
            }else{
                conta.setTipo(contasAPagarFactory.avaliarTipo(dados.getTipo()));
            }

            if (dados.getDataDeVencimento() == null){
                conta.setDataDeVencimento(conta.getDataDeVencimento());
                conta.setStatus(contasAPagarFactory.avaliarStatus(conta.getDataDeVencimento(), conta.getStatus()));
            }else {
                conta.setDataDeVencimento(dados.getDataDeVencimento());
                conta.setStatus(contasAPagarFactory.avaliarStatus(dados.getDataDeVencimento(), dados.getStatus()));
            }
            if (dados.getStatus() == StatusEnum.PAGO){
                conta.setDataDePagamento(LocalDateTime.now());
                conta.setStatus(dados.getStatus());
            }
            if (conta.getDataDePagamento() != null){
                conta.setStatus(StatusEnum.PAGO);
            }
            return contasAPagarRepository.save(conta);
        }
        throw new ContaNaoEncontradaException("Não encontrei a conta solicitada.");
    }
    public List<ExibirConta>listarContas(){
        List<ContasAPagarModel> conta = contasAPagarRepository.findAll();
        return ExibirConta.convert(conta);
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
