package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
