package com.isi.isivendor.service;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImplementation implements CategoriaService{

    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = repository.findById(id);
        if(categoria.isPresent()){
            return categoria.get();
        }
        else{
            throw new RuntimeException("Não foi possível encontrar esta categoria");
        }
    }
}
