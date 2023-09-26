package com.isi.isivendor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Instant instante;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "id_usuario")
    private Usuario usuario;

    public Pedido(){}

    public Pedido(Integer id, Instant instante, Usuario usuario) {
        this.id = id;
        this.instante = instante;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getInstante() {
        return instante;
    }

    public void setInstante(Instant instante) {
        this.instante = instante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
