package com.isi.isivendor.resource;

import com.isi.isivendor.entities.*;
import com.isi.isivendor.entities.DTO.*;
import com.isi.isivendor.repository.ItemPedidoRepository;
import com.isi.isivendor.service.PedidoService;
import com.isi.isivendor.service.ProdutoService;
import com.isi.isivendor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private UsuarioService serviceUsuario;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItemPedidoRepository pedidoRepository;

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

    /*
    @PostMapping
    public ResponseEntity<Pedido> postPedido(@RequestBody PedidoUsuarioDTO pedidoUsuarioDTO){

        Pedido pedido = pedidoUsuarioDTO.getPedido();
        Usuario usuario = pedidoUsuarioDTO.getUsuario();

        try{
            pedido.setInstante(Instant.now());
            Integer id = usuario.getId();
            Usuario aux = serviceUsuario.findById(id);
            pedido.setUsuario(aux);
            pedido =  service.insert(pedido);

        }
        catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(pedido);
    }
     */

    @PostMapping
    public ResponseEntity<Pedido> postPedido(@RequestBody PedidoUsuarioItemDTO pedidoUsuarioItemDTO){

        Pedido pedido = pedidoUsuarioItemDTO.getPedido();
        Usuario usuario = pedidoUsuarioItemDTO.getUsuario();
        ItemPedidoDTO itemPedidoDTO= pedidoUsuarioItemDTO.getItemPedido();
        Pagamento pagamento = pedidoUsuarioItemDTO.getPagamento();
        ItemPedido itemPedido = new ItemPedido();


        try{
            pedido.setInstante(Instant.now());
            Integer idUsuario = usuario.getId();
            Usuario auxUsuario = serviceUsuario.findById(idUsuario);


            Integer idProduto = itemPedidoDTO.getProduto();
            Produto auxProduto = produtoService.findById(idProduto);
            Integer quantidade = itemPedidoDTO.getQuantidade();

            Integer idPagamento = pagamento.getId();
            Pagamento auxPagamento = service.findById(idPagamento).getPagamento();
            System.out.println(auxPagamento);

            Double preco = itemPedidoDTO.getPreco();

            itemPedido.setQuantidade(quantidade);
            itemPedido.setProduto(auxProduto);
            itemPedido.setPedido(pedido);
            itemPedido.setPreco(preco);


            System.out.println(itemPedido);
            pedido.setPagamento(auxPagamento);
            pedido.getItems().add(itemPedido);
            pedido.setUsuario(auxUsuario);
            pedido =  service.insert(pedido);

        }
        catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(pedido);
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Integer id, @RequestBody Pedido pedido){
        pedido = service.updatePedido(pedido, id);
        return ResponseEntity.ok().body(pedido);
    }

}
