package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Produto;

import com.isi.isivendor.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
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

    @PostMapping
    public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
        produto = service.insert(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @RequestBody Produto produto){
        produto = service.update(id,produto);
        return ResponseEntity.ok().body(produto);
    }
}
