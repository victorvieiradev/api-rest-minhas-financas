package com.mycompany.financas.repository;


import com.mycompany.financas.model.RecebimentoEnum;
import com.mycompany.financas.model.ReceitaModel;
import com.mycompany.financas.model.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<ReceitaModel, Integer> {
    List<ReceitaModel> findByStatus(StatusEnum status);
    List<ReceitaModel> findByRecebimento(RecebimentoEnum recebimento );
}
