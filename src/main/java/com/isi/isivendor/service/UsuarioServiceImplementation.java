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

    @Override
    public Usuario insert(Usuario user) {
        return repository.save(user);

    }
    @Override
    public void delete(Integer id){
        repository.deleteById(id);
    }

    @Override
    public Usuario update(Integer id, Usuario usuario) {
        Optional<Usuario> aux = repository.findById(id);
        if(aux.isPresent()){
            Usuario entidade = aux.get();
            updateData(entidade, usuario);
        }
        else{
            throw new RuntimeException("Usuario não encontrado para atualização!");
        }
        return repository.save(usuario);
    }

    private void updateData(Usuario aux, Usuario usuario ){
        aux.setNome(usuario.getNome());
        aux.setSobrenome(usuario.getSobrenome());
        aux.setEmail(usuario.getEmail());
        aux.setTelefone(usuario.getTelefone());
    }
}
