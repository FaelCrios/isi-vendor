package com.isi.isivendor.repository;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
