package com.isi.isivendor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String descricao;

    private Double price;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "id_produto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> items = new HashSet<>();


    public Produto(){}

    public Produto(Integer id, String name, String descricao, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.descricao = descricao;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    @JsonIgnore
    public Set<Pedido> getPedidos(){
        Set<Pedido> set = new HashSet<>();
        for(ItemPedido x: items){
            set.add(x.getPedido());
        }
        return set;
    }





}
