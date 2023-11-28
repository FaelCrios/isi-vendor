package com.isi.isivendor.repository;

import com.isi.isivendor.entities.Produto;
import com.isi.isivendor.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    @Query("SELECT p FROM Produto p WHERE :categoria MEMBER OF p.categorias")
    Produto buscarPorCategoria(@Param("categoria") String categoria);

}
