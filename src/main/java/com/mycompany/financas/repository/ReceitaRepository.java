package com.mycompany.financas.repository;


import com.mycompany.financas.model.RecebimentoEnum;
import com.mycompany.financas.model.ReceitaModel;
import com.mycompany.financas.model.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReceitaRepository extends JpaRepository<ReceitaModel, Integer> {
    List<ReceitaModel> findByStatus(StatusEnum status);
    List<ReceitaModel> findByRecebimento(RecebimentoEnum recebimento );
    List<ReceitaModel> findBydataDeVencimento(LocalDate dataDeVencimento);
}
