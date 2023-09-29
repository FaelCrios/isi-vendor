package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Produto;

import com.isi.isivendor.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> getAllproduto(){
        List<Produto> lista= service.findAll();
        return ResponseEntity.ok().body(lista);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> getprodutoById(@PathVariable Integer id){
        Produto produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }

}
