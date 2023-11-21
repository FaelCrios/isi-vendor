package com.isi.isivendor.entities.DTO;

public class PedidoUsuarioItemPedidoPagamentoDTO {

    private Integer pedido;

    private Integer usuario;

    private Integer produto;

    private Integer quantidade;

    private Integer pagamento;

    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getPagamento() {
        return pagamento;
    }

    public void setPagamento(Integer pagamento) {
        this.pagamento = pagamento;
    }
}