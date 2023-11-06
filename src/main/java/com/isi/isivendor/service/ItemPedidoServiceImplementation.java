package com.isi.isivendor.service;

import com.isi.isivendor.entities.ItemPedido;
import com.isi.isivendor.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoServiceImplementation implements ItemPedidoService{

    @Autowired
    private ItemPedidoRepository repository;

    @Override
    public List<ItemPedido> findAll() {
        return repository.findAll();
    }

    @Override
    public ItemPedido findById(Integer id) {
        Optional<ItemPedido> itemPedido = repository.findById(id);
        if(itemPedido.isPresent()){
            return itemPedido.get();
        }
        else{
            throw new RuntimeException("Não foi possível encontrar esta ItemCategoria");
        }
    }
    @Override
    public ItemPedido insert(ItemPedido itemPedido) {
        return repository.save(itemPedido);
    }

    @Override
     public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
     public ItemPedido update(Integer id, ItemPedido itemPedido) {
        Optional<ItemPedido> aux = repository.findById(id);
         ItemPedido auxiliar;
        if(aux.isPresent()){
            auxiliar = updateData(aux.get(), itemPedido);
        }else{
            throw new RuntimeException("ItemCategoria não encontrada para ser alterada");
        }
        return repository.save(auxiliar);
    }


    private ItemPedido updateData(ItemPedido aux, ItemPedido itemPedido){
        aux.setPedido(itemPedido.getPedido());
        aux.setPreco(itemPedido.getPreco());
        aux.setProduto(itemPedido.getProduto());
        aux.setQuantidade(itemPedido.getQuantidade());
        return aux;
    }
}
