package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@Tag(name="Categoria", description = "EndPoints para gerenciamento das categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    @Operation(
            summary = "Encontra todas as categorias",
            description = "Encontra todas as categorias cadastradas no nosso sistema",
            tags = {"Categoria"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class))
                                    )
                            }
                    ),
                    @ApiResponse(description = "Erro Interno", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<Categoria>> getAllCategoria(){
        List<Categoria> lista= service.findAll();
        return ResponseEntity.ok().body(lista);

    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Encontra uma categoria",
    description = "Encontra uma categoria pelo ID",
    tags = {"Categoria"},
    responses = {
            @ApiResponse(
                    description = "Sucesso",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))
            ),
            @ApiResponse(description = "Não foi encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Erro Interno", responseCode = "505", content = @Content)
    })
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id){
        Categoria categoria = service.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    @Operation(
            summary = "Adiciona uma nova categoria",
            description = "Adiciona uma nova categoria na nossa base de dados, usando uma representação JSON",
            tags = {"Categoria"},
            responses = {
                    @ApiResponse(
                            description = "Criado",
                            responseCode = "201",
                            links = @Link(name="get",operationId = "get", parameters = @LinkParameter(name = "id", expression = "$request.body.id")),
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))
                    ),
                    @ApiResponse(
                            description = "Erro interno",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);

    }


    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Delete uma categoria",
            description = "Deleta uma categoria pelo ID correspondente",
            tags = {"Categoria"},
            responses = {
                    @ApiResponse(
                            description = "Deletado", responseCode = "204", content = @Content
                    ),
                    @ApiResponse(
                            description = "Não encontrado", responseCode = "404", content = @Content
                    ),
                    @ApiResponse(
                            description = "Erro interno", responseCode = "500", content = @Content
                    )
            }
    )
    public ResponseEntity<Categoria> DeleteCategoria(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/{id}")
    @Operation(
            summary = "Atualiza as informações da categoria",
            description = "Atualiza as informações da categoria selecionando pelo ID da mesma",
            tags = {"Categoria"},
            responses = {
                    @ApiResponse(
                            description = "Atualizado",
                            responseCode = "200",
                            links = @Link(name = "get",operationId = "get", parameters = @LinkParameter(name="id",expression = "$request.body.id")),
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))
                    ),
                    @ApiResponse(
                            description = "Não encontrado",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Erro interno",
                            responseCode = "500",
                            content = @Content

                    )
            }
    )
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria){
        categoria = service.update(id, categoria);
        return ResponseEntity.ok().body(categoria);
    }

}
