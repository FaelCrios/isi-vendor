package com.isi.isivendor.config;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Produto;
import com.isi.isivendor.entities.Usuario;
import com.isi.isivendor.entities.enums.PedidoStatus;
import com.isi.isivendor.repository.CategoriaRepository;
import com.isi.isivendor.repository.PedidoRepository;
import com.isi.isivendor.repository.ProdutoRepository;
import com.isi.isivendor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

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

    @Override
    public void run(String... args) throws Exception {
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

    }
}
