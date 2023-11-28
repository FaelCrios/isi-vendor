package com.isi.isivendor.resource;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.DTO.ProdutoCategoriaDTO;
import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Produto;

import com.isi.isivendor.service.CategoriaService;
import com.isi.isivendor.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @Operation(
            summary = "Encontra todos os produtos",
            description = "Encontra todos os nossos produtos cadastrados no nosso sistema",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Produto.class))
                                    )
                            }
                    ),
                    @ApiResponse(description = "Erro Interno", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<Produto>> getAllproduto(){
        List<Produto> lista= service.findAll();
        return ResponseEntity.ok().body(lista);

    }


    @GetMapping(value = "/categoria/{categoria}")
    @Operation(
            summary = "Encontra um produto pela categoria",
            description = "Encontra um produto atraves de uma categoria",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))
                    ),
                    @ApiResponse(description = "Não foi encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro Interno", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<Produto>> getByCategoriaDoProduto(@PathVariable String categoria) {
        List<Produto> produtos = service.getByCategoria(categoria);
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping(value = "/id/{id}")
    @Operation(
            summary = "Encontra um produto pelo ID",
            description = "Encontra um produto atraves do ID",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))
                    ),
                    @ApiResponse(description = "Não foi encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro Interno", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Produto> getprodutoById(@PathVariable Integer id) {
        Produto produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }




    @PostMapping
    @Operation(
            summary = "Adiciona um novo produto",
            description = "Adiciona um novo produto na nossa base de dados, usando uma representação JSON",
            tags ={"Produto"},
            responses = {
                    @ApiResponse(
                            description = "Criado",
                            responseCode = "201",
                            links = @Link(name="get", operationId = "get", parameters = @LinkParameter(name = "id", expression = "request.body.id")),
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))
                    ),
                    @ApiResponse(
                            description = "Erro Interno",
                            responseCode = "500",
                            content = @Content
                    )
            }

    )
    public ResponseEntity<Produto> postProduto(@RequestBody ProdutoCategoriaDTO produtoCategoriaDTO){
        Produto produto = produtoCategoriaDTO.getProduto();
        Integer categoria = produtoCategoriaDTO.getCategoria();

        try {
            Categoria categoriaAux = categoriaService.findById(categoria);
            produto.getCategorias().add(categoriaAux);
            service.insert(produto);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(produto);
    }


    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Delete um produto",
            description = "Deleta um produto pelo ID correspondente",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(
                            description = "Deletado", responseCode = "204", content = @Content
                    ),
                    @ApiResponse(
                            description = "Não encontrado", responseCode = "404", content = @Content
                    ),
                    @ApiResponse(
                            description = "Erro Interno", responseCode = "500", content = @Content
                    )
            }
    )
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(
            summary = "Atualiza as informações do produto",
            description = "Atualiza as informações de um produto selecionado pelo ID",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(
                            description = "Atualizado",
                            responseCode = "200",
                            links = @Link(name = "get", operationId = "get", parameters = @LinkParameter(name = "id", expression = "$request.body.id")),
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))
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
    public ResponseEntity<Produto> updateProduto(@PathVariable Integer id, @RequestBody Produto produto){
        produto = service.update(id,produto);
        return ResponseEntity.ok().body(produto);
    }
}
