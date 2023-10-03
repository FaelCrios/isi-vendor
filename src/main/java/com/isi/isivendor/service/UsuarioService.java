package com.isi.isivendor.service;

import com.isi.isivendor.entities.Usuario;
import com.isi.isivendor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UsuarioService {



    public List<Usuario> findAll();

    public Usuario findById(Integer id);

    public Usuario insert(Usuario user);

    public void delete(Integer id);

    public Usuario update(Integer id, Usuario usuario);


}
