package com.isi.isivendor.config;

import com.isi.isivendor.entities.*;
import com.isi.isivendor.entities.enums.PedidoStatus;
import com.isi.isivendor.repository.*;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public void run(String... args) throws Exception {

       /*
        Usuario u1 = new Usuario(null,"Rafael","Colin Rios","email@email.com","16999990000","123456");
        Usuario u2 = new Usuario(null,"Rafael","Colin Rios","email@email.com","16999990000","123456");

        Pedido p1 = new Pedido(null, Instant.now(),u1, PedidoStatus.CANCELADO);
        Pedido p2 = new Pedido(null, Instant.now(),u1,PedidoStatus.A_CAMINHO);

        Categoria c1 = new Categoria(null, "D20");

        Produto prod1 = new Produto(null, "Dado RPG", "LOREM IPSUM LOREM IPSUM", 25.12, "WWW.GOOGLE.COM");
        prod1.getCategorias().add(c1);

        usuarioRepository.saveAll(Arrays.asList(u1,u1));
        System.out.println("AL");
        pedidoRepository.saveAll(Arrays.asList(p1,p2));
        categoriaRepository.save(c1);
        produtoRepository.save(prod1);

        ItemPedido ip1 = new ItemPedido(prod1, p1, 3,prod1.getPrice());
        ItemPedido ip2 = new ItemPedido(prod1, p2, 3,prod1.getPrice());

        itemPedidoRepository.save(ip1);
        itemPedidoRepository.save(ip2);


        Pagamento pag1 = new Pagamento(null, Instant.now(),p1);
        Pagamento pag2 = new Pagamento(null, Instant.now(),p2);

        p1.setPagamento(pag1);
        p2.setPagamento(pag2);
        pedidoRepository.saveAll(Arrays.asList(p1,p2));
        */

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getDefaultUserName(), getPasswordAuthentication().getPassword());
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("rcolinrios@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("faelnek@gmail.com"));
        message.setSubject("Mail Subject");

        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);


    }
}
