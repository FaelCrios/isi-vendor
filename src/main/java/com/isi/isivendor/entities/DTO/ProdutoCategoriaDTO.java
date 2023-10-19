package com.isi.isivendor.entities.DTO;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Produto;

public class ProdutoCategoriaDTO {

    private Produto produto;

    private Integer categoria;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
}
