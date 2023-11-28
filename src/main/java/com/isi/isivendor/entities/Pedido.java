package com.isi.isivendor.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isi.isivendor.entities.enums.PedidoStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant instante;

    @Column(name = "status_pedido")
    private Integer pedidoStatus;

    @ManyToOne
    @JoinColumn(name= "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> items = new HashSet<>();


    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    public Pedido(){}

    public Pedido(Integer id, Instant instante, Usuario usuario, PedidoStatus pedidoStatus) {
        this.id = id;
        this.instante = instante;
        this.usuario = usuario;
        setPedidoStatus(pedidoStatus);
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

    public PedidoStatus getPedidoStatus() {
        return PedidoStatus.valor(pedidoStatus);
    }

    public void setPedidoStatus(PedidoStatus pedidoStatus) {
        if(pedidoStatus != null){
            this.pedidoStatus = pedidoStatus.getCode();
        }
    }

    public Set<ItemPedido> getItems() {
        return items;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Double getTotal(){
        double soma = 0.0;
        for(ItemPedido x : items){
            soma += x.GetSubTotal();
        }
        return soma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(instante, pedido.instante) && Objects.equals(pedidoStatus, pedido.pedidoStatus) && Objects.equals(usuario, pedido.usuario) && Objects.equals(items, pedido.items) && Objects.equals(pagamento, pedido.pagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instante, pedidoStatus, usuario, items, pagamento);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "instante=" + instante +
                ", pedidoStatus=" + pedidoStatus +
                ", usuario=" + usuario +
                ", items=" + items +
                ", pagamento=" + pagamento +
                '}';
    }
}
