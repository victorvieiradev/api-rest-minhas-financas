package com.mycompany.financas.repository;

import com.mycompany.financas.model.EstadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Integer> {
    boolean existsByUf(String uf);
    boolean existsByNomeEstado(String nomeEstado);
}
