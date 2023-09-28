package com.isi.isivendor.service;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Pedido;

import java.util.List;

public interface CategoriaService {

    public List<Categoria> findAll();

    public Categoria findById(Integer id);

}
