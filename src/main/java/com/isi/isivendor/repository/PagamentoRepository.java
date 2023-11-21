package com.isi.isivendor.repository;

import com.isi.isivendor.entities.Categoria;
import com.isi.isivendor.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Integer> {
}
