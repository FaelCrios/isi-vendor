package com.isi.isivendor.resource;

import com.isi.isivendor.entities.*;
import com.isi.isivendor.entities.DTO.*;
import com.isi.isivendor.entities.enums.PedidoStatus;
import com.isi.isivendor.repository.*;
import com.isi.isivendor.service.ItemPedidoService;
import com.isi.isivendor.service.PedidoService;
import com.isi.isivendor.service.ProdutoService;
import com.isi.isivendor.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Time;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
@Tag(name="Pedido", description = "EndPoint para gerenciamento das requisições dos nossos pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService serviceUsuario;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping
    @Operation(
            summary = "Encontra todos os pedidos",
            description = "Encontra todos os pedidos cadastrados na nossa base de dados",
            tags = {"Pedido"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Pedido.class))
                                    )
                            }
                    ),
                    @ApiResponse(description = "Erro interno", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<Pedido>> getAllPedido(){
        List<Pedido> pedidos = service.findAll();
        return ResponseEntity.ok().body(pedidos);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Encontra um pedido",
    description = "Encontra um pedido pelo ID",
    tags = {"Pedido"},
            responses = {
            @ApiResponse(
                    description = "Sucesso",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))
            ),
                    @ApiResponse(description = "Não foi encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro interno", responseCode = "500", content = @Content)

            }
    )
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id){
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok().body(pedido);
    }



    @PostMapping
    @Operation(
            summary = "Adiciona um novo pedido",
            description = "Adiciona um novo pedido, recebendo ele e suas entidades aninhas sendo representadas por JSON",
            tags = {"Pedido"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))
                    ),
                    @ApiResponse(description = "Não foi encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro interno", responseCode = "500", content = @Content)
            }
    )
    /*public ResponseEntity<Pedido> postPedido(@RequestBody PedidoUsuarioItemDTO pedidoUsuarioItemDTO){

        //Pedido pedido = pedidoUsuarioItemDTO.getPedido();
        Pedido pedido = new Pedido();
        pedido.setInstante(Instant.now());

        Usuario usuarioDTO = pedidoUsuarioItemDTO.getUsuario();
        Usuario auxUsuario = usuarioRepository.getReferenceById(usuarioDTO.getId());
        pedido.setUsuario(auxUsuario);

        ItemPedidoDTO itemPedidoDTO= pedidoUsuarioItemDTO.getItemPedido();
        Pagamento pagamento = pedidoUsuarioItemDTO.getPagamento();


        try{
           // Integer idUsuario = usuario.getId();
           // Usuario auxUsuario = serviceUsuario.findById(idUsuario);


            Integer idProduto = itemPedidoDTO.getProduto();
            Produto auxProduto = produtoRepository.getReferenceById(idProduto);

            Integer quantidade = itemPedidoDTO.getQuantidade();


            Integer idPagamento = pagamento.getId();
            Pagamento auxPagamento = service.findById(idPagamento).getPagamento();

            pedido.setPagamento(auxPagamento);
            pedido.setUsuario(auxUsuario);
            System.out.println(pedido);


            ItemPedido itemPedido = new ItemPedido(auxProduto,pedido,quantidade, auxProduto.getPrice());
            System.out.println(itemPedido);


            produtoService.insert(auxProduto);
           // itemPedidoService.insert(itemPedido);
            itemPedidoRepository.saveAndFlush(itemPedido);
            service.insert(pedido);


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

    public ResponseEntity<Pedido> postPedido(@RequestBody PedidoUsuarioItemPedidoPagamentoDTO dto){

        Pedido pedido = new Pedido();

        try{

            pedido.setInstante(Instant.now());

            pedido.setPedidoStatus(PedidoStatus.valor(dto.getPedido()));
            Usuario usuarioDTO = serviceUsuario.findById(dto.getUsuario());
            pedido.setUsuario(usuarioDTO);

            Integer idProduto = dto.getProduto();
            Produto auxProduto= produtoRepository.getReferenceById(idProduto);
            ItemPedido itemPedido = new ItemPedido(auxProduto,pedido,dto.getQuantidade(),auxProduto.getPrice());


            Pagamento pag = new Pagamento(null, Instant.now(), pedido);

            pedido.setPagamento(pag);
            System.out.println(pedido);

            System.out.println(itemPedido);
          //  itemPedidoRepository.save(itemPedido);

            service.insert(pedido);


        }
        catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).build();
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
