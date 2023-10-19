package com.isi.isivendor.entities.DTO;

import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Usuario;

public class PedidoUsuarioDTO {
    private Pedido pedido;

    private Usuario usuario;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
