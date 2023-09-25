package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Usuario;
import com.isi.isivendor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllFuncionario(){
        List<Usuario> usuarios = service.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

}
