package com.isi.isivendor.service;

import com.isi.isivendor.entities.Produto;
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

    @Override
    public Produto getByCategoria(String categoria){
        Produto produto = repository.buscarPorCategoria(categoria);
        if(produto != null) {
            return produto;
        }
        else{
            throw new RuntimeException("Produto não foi encontrado");
        }
    }

    @Override
    public Produto insert(Produto produto) {

        return repository.save(produto);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Produto update(Integer id, Produto produto) {
        Optional<Produto> aux = repository.findById(id);
        Produto auxiliar;
        if(aux.isPresent()){
            auxiliar = updateData(aux.get(), produto);
        }else{
            throw new RuntimeException("Produto não encontrado para ser atualizado");
        }
        return repository.save(auxiliar);
    }



    private Produto updateData(Produto aux, Produto produto){
        aux.setName(produto.getName());
        aux.setDescricao(produto.getDescricao());
        aux.setPrice(produto.getPrice());
        return aux;
    }
}
