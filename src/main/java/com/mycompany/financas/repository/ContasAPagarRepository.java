package com.mycompany.financas.repository;
import com.mycompany.financas.model.ContasAPagarModel;
import com.mycompany.financas.model.StatusEnum;
import com.mycompany.financas.model.TipoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContasAPagarRepository extends JpaRepository<ContasAPagarModel, Long> {
    List<ContasAPagarModel> findByStatus(StatusEnum status);
    List<ContasAPagarModel> findByTipo(TipoEnum tipo);
}
