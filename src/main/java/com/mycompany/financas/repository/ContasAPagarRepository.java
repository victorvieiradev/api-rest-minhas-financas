package com.mycompany.financas.repository;
import com.mycompany.financas.model.ContasAPagarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContasAPagarRepository extends JpaRepository<ContasAPagarModel, Long> {
    List<ContasAPagarModel> findByStatusIgnoreCase(String status);
    List<ContasAPagarModel> findByTipoIgnoreCase(String tipo);
}
