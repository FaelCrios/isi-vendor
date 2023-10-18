package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.service.PedidoService;
import com.isi.isivendor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private UsuarioService serviceUsuario;

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

    @PostMapping
    public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido){
         pedido =  service.addPedido(pedido);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}").buildAndExpand(pedido.getId()).toUri();
         return ResponseEntity.created(uri).body(pedido);

    }
}
