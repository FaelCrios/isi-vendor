package com.isi.isivendor.repository;

import com.isi.isivendor.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select Usuario from Usuario where Usuario.email =:email")
    Usuario findByEmail(@Param("email") String email);
}
