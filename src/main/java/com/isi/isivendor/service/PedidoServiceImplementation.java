package com.isi.isivendor.service;

import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Produto;
import com.isi.isivendor.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImplementation implements PedidoService{

    @Autowired
    private PedidoRepository repository;

    @Override
    public List<Pedido> findAll() {
        return repository.findAll();
    }

    @Override
    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = repository.findById(id);
        if(pedido.isPresent()) {
            return pedido.get();
        }
        else{
            throw new RuntimeException("Pedido não foi encontrado");
        }
    }

    @Override
    public Pedido addPedido(Pedido pedido) {
        return repository.save(pedido);
    }

    @Override
    public Pedido updatePedido(Pedido pedido, Integer id){
        Optional<Pedido> aux = repository.findById(id);
        Pedido auxiliar;
        if(aux.isPresent()){
            auxiliar = updateData(aux.get(),pedido);
        }
        else {
            throw new RuntimeException("Seu pedido não pode ser atualizado!");
        }
        return repository.save(auxiliar);
    }


    private Pedido updateData(Pedido aux, Pedido pedido){
        aux.setUsuario(pedido.getUsuario());
        aux.setPagamento(pedido.getPagamento());
        aux.setPedidoStatus(pedido.getPedidoStatus());
        aux.setInstante(pedido.getInstante());
        return aux;
    }

}
