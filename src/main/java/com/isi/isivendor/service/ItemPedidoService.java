package com.isi.isivendor.service;

import com.isi.isivendor.entities.ItemPedido;

import java.util.List;

public interface ItemPedidoService {

    public List<ItemPedido> findAll();

    public ItemPedido findById(Integer id);

    public ItemPedido insert(ItemPedido itemPedido);
    public void delete(Integer id);

    public ItemPedido update(Integer id, ItemPedido itemPedido);


}
