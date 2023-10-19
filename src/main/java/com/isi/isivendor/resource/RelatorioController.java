package com.isi.isivendor.resource;
import com.isi.isivendor.entities.*;
import com.isi.isivendor.service.EmailService;
import com.isi.isivendor.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private PedidoService services;

    @Autowired
    private EmailService emailService;



    @GetMapping
    public ResponseEntity<Void> sendRelatorio(@RequestBody Map<String, String> requestBody) {
        try {
            String email = requestBody.get("email"); // Extrai o valor do campo "email" do JSON
            List<Pedido> pedidos = services.findAll();
            String subject = "E-COMMERCE APPLICATION | Envio de Relatório";

            StringBuilder message = new StringBuilder("Lista de Pedidos:\n");

            for (Pedido pedido : pedidos) {
                appendPedidoDetails(message, pedido);
            }

            emailService.sendEmail(email, subject, message.toString());

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Lidar com exceções ao enviar o e-mail
            e.printStackTrace(); // Registre a exceção ou trate de acordo com suas necessidades
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    private void appendPedidoDetails(StringBuilder message, Pedido pedido) {
        message.append("-----------------------------------------------------\n");
        message.append("ID do Pedido: ").append(pedido.getId()).append("\n");
        message.append("Data do Pedido: ").append(pedido.getInstante()).append("\n");
        message.append("Status do pedido: ").append(pedido.getPedidoStatus()).append("\n");
        message.append("Id do usuario: ").append(pedido.getUsuario().getId()).append("\n");
        message.append("Nome do Usuário: ").append(pedido.getUsuario().getNome()).append(" ").append(pedido.getUsuario().getSobrenome()).append("\n");
        message.append("Email: ").append(pedido.getUsuario().getEmail()).append("\n");
        message.append("Telefone: ").append(pedido.getUsuario().getTelefone()).append("\n");
        message.append("Itens:\n");

        for (ItemPedido item : pedido.getItems()) {
            appendItemDetails(message, item);
        }

        message.append("Preço total: ").append(pedido.getTotal()).append("\n");
        message.append("Momento do pagamento: ").append(pedido.getPagamento().getMomento()).append("\n");
    }

    private void appendItemDetails(StringBuilder message, ItemPedido item) {
        message.append("Quantidade: ").append(item.getQuantidade()).append("\n");
        message.append("Preço: ").append(item.getPreco()).append("\n");
        message.append("Produto:\n");
        message.append("Id do produto: ").append(item.getProduto().getId()).append("\n");
        message.append("Nome do produto: ").append(item.getProduto().getName()).append("\n");
        message.append("Descrição do produto: ").append(item.getProduto().getDescricao()).append("\n");
        message.append("Preço do produto: ").append(item.getProduto().getPrice()).append("\n");
        message.append("Categoria do produto:\n");

        for (Categoria categoria : item.getProduto().getCategorias()) {
            appendCategoriaDetails(message, categoria);
        }
    }

    private void appendCategoriaDetails(StringBuilder message, Categoria categoria) {
        message.append("ID da categoria: ").append(categoria.getId()).append("\n");
        message.append("Nome da categoria: ").append(categoria.getNome()).append("\n");
    }







}
