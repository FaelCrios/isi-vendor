package com.isi.isivendor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos = new HashSet<>();

    public Categoria(){
    }

    public Categoria(Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

}
