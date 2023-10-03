package com.isi.isivendor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isi.isivendor.entities.pk.ItemPedidoPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="item_pedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ItemPedidoPK id = new ItemPedidoPK();

    private Integer quantidade;
    private Double preco;

    public ItemPedido(){}

    public ItemPedido(Produto produto,Pedido pedido,Integer quantidade, Double preco){
        id.setPedido(pedido);
        id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }

    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }

    public Produto getProduto(){
        return id.getProduto();
    }

    public void setProduto(Produto Produto){
        id.setProduto(Produto);
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public Double GetSubTotal(){
        return preco * quantidade;
    }

}
