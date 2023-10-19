package com.isi.isivendor.entities.DTO;

import com.isi.isivendor.entities.ItemPedido;
import com.isi.isivendor.entities.Pagamento;
import com.isi.isivendor.entities.Pedido;
import com.isi.isivendor.entities.Usuario;
import com.isi.isivendor.entities.pk.ItemPedidoPK;

public class PedidoUsuarioItemDTO {

    private Pedido pedido;
    private Usuario usuario;
    private ItemPedidoDTO itemPedido;

    private Pagamento pagamento;


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

    public ItemPedidoDTO getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedidoDTO itemPedido) {
        this.itemPedido = itemPedido;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
