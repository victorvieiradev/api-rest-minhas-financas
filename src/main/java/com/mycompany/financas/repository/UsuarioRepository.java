package com.mycompany.financas.repository;

import com.mycompany.financas.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

}
