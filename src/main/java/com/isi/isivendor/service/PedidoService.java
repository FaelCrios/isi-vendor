package com.isi.isivendor.service;

import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Usuario;

import java.util.List;

public interface PedidoService {



    public List<Pedido> findAll();

    public Pedido findById(Integer id);

    public Pedido addPedido(Pedido pedido);

    public Pedido updatePedido(Pedido pedido, Integer id);

}
