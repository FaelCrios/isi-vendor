package com.isi.isivendor.resource;

import com.isi.isivendor.entities.DTO.CredenciaisDTO;
import com.isi.isivendor.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Autentica um usuario e retorna um token de autenticação")
    @PostMapping(value = "/signin")
    public ResponseEntity logar(@RequestBody CredenciaisDTO data){
        if(data == null || data.getEmail() == null || data.getEmail().isBlank() || data.getSenha() == null || data.getSenha().isBlank()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario invalido");
        }
            var token = authService.signin(data);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario invalido");

        }
            return token;
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Atualiza o token de acesso para um usuario autorizado e retorna um token ")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshtoken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken){
        if(refreshToken == null || refreshToken.isBlank() || username.isBlank()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario invalido");
        }
        var token = authService.refreshToken(username,refreshToken);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario invalido");
        }
        return token;
    }
}
