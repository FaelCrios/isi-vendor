package com.isi.isivendor.service;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Produto;

import java.util.List;

public interface ProdutoService {

    public List<Produto> findAll();

    public Produto findById(Integer id);

}
