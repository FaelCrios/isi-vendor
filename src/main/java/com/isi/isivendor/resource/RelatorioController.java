package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Usuario;
import com.isi.isivendor.service.EmailService;
import com.isi.isivendor.service.PedidoService;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private PedidoService services;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<Void> sendRelatorio(){
        List<Pedido> pedidos = services.findAll();
        String subject = "E-COMMERCE APPLICATION | Envio de Relatório";
        String message = pedidos.toString();

        emailService.sendEmail("faelnek@gmail.com",subject,message);
        return ResponseEntity.noContent().build();
    }

}
