package com.isi.isivendor.service;

import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Produto;
import com.isi.isivendor.repository.PedidoRepository;
import com.isi.isivendor.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImplementation implements ProdutoService{

    @Autowired
    private ProdutoRepository repository;

    @Override
    public List<Produto> findAll() {
        return repository.findAll();
    }

    @Override
    public Produto findById(Integer id) {
        Optional<Produto> produto = repository.findById(id);
        if(produto.isPresent()) {
            return produto.get();
        }
        else{
            throw new RuntimeException("Produto não foi encontrado");
        }
    }
}
