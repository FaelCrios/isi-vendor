package com.isi.isivendor.service;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Produto;
import com.isi.isivendor.entities.Usuario;

import java.util.List;

public interface ProdutoService {

    public List<Produto> findAll();

    public Produto findById(Integer id);

    Produto getByCategoria(String categoria);

    public Produto insert(Produto produto);

    public void delete(Integer id);

    public Produto update(Integer id, Produto produto);


}
