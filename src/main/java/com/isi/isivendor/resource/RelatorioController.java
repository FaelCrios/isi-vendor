package com.isi.isivendor.resource;

import com.isi.isivendor.entities.*;
import com.isi.isivendor.service.EmailService;
import com.isi.isivendor.service.PedidoService;
import com.isi.isivendor.service.UsuarioService;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private PedidoService services;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService userService;

    @GetMapping
    public ResponseEntity<Void> sendRelatorio(@RequestBody String email) {
        List<Pedido> pedidos = services.findAll();
        String subject = "E-COMMERCE APPLICATION | Envio de Relatório";

        // Construa uma mensagem personalizada
        StringBuilder message = new StringBuilder("Lista de Pedidos:\n");
        for (Pedido pedido : pedidos) {
            message.append("-----------------------------------------------------").append("\n");
            message.append("ID do Pedido: ").append(pedido.getId()).append("\n");
            message.append("Data do Pedido: ").append(pedido.getInstante()).append("\n");
            message.append("Status do pedido: ").append(pedido.getPedidoStatus()).append("\n"); // Status da compra
            message.append("Id do usuario: ").append(pedido.getUsuario().getId()).append("\n");;
            message.append("Nome do Usuário: ").append(pedido.getUsuario().getNome()).append(" ").append(pedido.getUsuario().getSobrenome()).append("\n"); // Obtenha o nome do usuário
            message.append("Email: ").append(pedido.getUsuario().getEmail()).append("\n");
            message.append("Telefone: ").append(pedido.getUsuario().getTelefone()).append("\n");
            message.append("Itens: ").append("\n\n");
            message.append("Quantidade: ").append(pedido.getItems().stream().map(ItemPedido::getQuantidade).collect(Collectors.toSet())).append('\n');
            message.append("Preço: ").append(pedido.getItems().stream().map(ItemPedido::getPreco).collect(Collectors.toSet())).append('\n');
            message.append("Produto: ").append("\n\n");
            message.append("Id do produto: ").append(pedido.getItems().stream().map(ItemPedido::getProduto).map(Produto::getId).collect(Collectors.toSet())).append('\n');
            message.append("Nome do produto: ").append(pedido.getItems().stream().map(ItemPedido::getProduto).map(Produto::getName).collect(Collectors.toSet())).append('\n');
            message.append("Descrição do produto: ").append(pedido.getItems().stream().map(ItemPedido::getProduto).map(Produto::getDescricao).collect(Collectors.toSet())).append('\n');
            message.append("Preço do produto: ").append(pedido.getItems().stream().map(ItemPedido::getProduto).map(Produto::getPrice).collect(Collectors.toSet())).append("\n\n");
            message.append("Categoria do produto: ").append('\n');
            message.append("ID da categoria: ").append(pedido.getItems().stream().map(ItemPedido::getProduto).flatMap(produto -> produto.getCategorias().stream()).map(Categoria::getId).collect(Collectors.toSet()));
            message.append("Nome da categoria: ").append(pedido.getItems().stream().map(ItemPedido::getProduto).flatMap(produto -> produto.getCategorias().stream()).map(Categoria::getNome).collect(Collectors.toSet()));
            message.append("\n");
            message.append("\n");
            message.append("Pagamento: ").append("\n");
            message.append("Preço total: ").append(pedido.getTotal());
            message.append("\n");
            message.append("Momento do pagamento: ").append(pedido.getPagamento().getMomento()).append("\n");

        }

        emailService.sendEmail(email, subject, message.toString());
        return ResponseEntity.noContent().build();
    }






}
