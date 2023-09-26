package com.isi.isivendor.service;

import com.isi.isivendor.entities.Usuario;
import com.isi.isivendor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImplementation implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()) {
            return usuario.get();
        }
        else{
            throw new RuntimeException("Usuario não foi encontrado");
        }
    }
}
