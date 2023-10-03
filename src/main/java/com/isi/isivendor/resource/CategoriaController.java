package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.service.CategoriaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategoria(){
        List<Categoria> lista= service.findAll();
        return ResponseEntity.ok().body(lista);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id){
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria){
        categoria = service.update(id, categoria);
        return ResponseEntity.ok().body(categoria);
    }

}
