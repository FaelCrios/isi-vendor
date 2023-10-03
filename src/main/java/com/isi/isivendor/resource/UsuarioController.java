package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Usuario;
import com.isi.isivendor.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario(){
        List<Usuario> usuarios = service.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id){
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario user){
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Integer id){
        //Usuario usuario = service.findById(id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
