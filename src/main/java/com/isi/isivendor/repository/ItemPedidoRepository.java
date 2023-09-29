package com.isi.isivendor.repository;

import com.isi.isivendor.entities.ItemPedido;
import com.isi.isivendor.entities.pk.ItemPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
