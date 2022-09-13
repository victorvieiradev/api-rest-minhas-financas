package com.mycompany.financas.repository;

import com.mycompany.financas.model.CidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeModel, Integer> {
    boolean existsBynomeCidade(String cidade);

}
