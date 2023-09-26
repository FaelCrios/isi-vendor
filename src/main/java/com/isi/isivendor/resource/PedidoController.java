package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedido(){
        List<Pedido> pedidos = service.findAll();
        return ResponseEntity.ok().body(pedidos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id){
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok().body(pedido);
    }
}
