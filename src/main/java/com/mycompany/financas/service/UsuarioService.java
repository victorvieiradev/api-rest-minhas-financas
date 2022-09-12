package com.mycompany.financas.service;

import com.mycompany.financas.model.UsuarioModel;
import com.mycompany.financas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Transactional
    public UsuarioModel salvarUsuario(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
    }
    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    public boolean existsByCpf(String cpf){
        return usuarioRepository.existsByCpf(cpf);
    }
    public List<UsuarioModel> listarUsuario(){
        return usuarioRepository.findAll();
    }
    public Optional<UsuarioModel> buscarPorId(Integer id ){
        return usuarioRepository.findById(id);
    }
    @Transactional
    public void excluir(UsuarioModel usuarioModel){
        usuarioRepository.delete(usuarioModel);
    }

}
