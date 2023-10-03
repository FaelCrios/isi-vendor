package com.isi.isivendor.service;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Produto;

import java.util.List;

public interface CategoriaService {

    public List<Categoria> findAll();

    public Categoria findById(Integer id);

    public Categoria insert(Categoria categoria);

    public void delete(Integer id);

    public Categoria update(Integer id, Categoria categoria);


}
