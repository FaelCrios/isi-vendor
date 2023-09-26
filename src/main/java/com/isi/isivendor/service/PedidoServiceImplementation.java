package com.isi.isivendor.service;

import com.isi.isivendor.entities.Pedido;
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
        Optional<Pedido> Pedido = repository.findById(id);
        if(Pedido.isPresent()) {
            return Pedido.get();
        }
        else{
            throw new RuntimeException("Pedido não foi encontrado");
        }
    }
}
