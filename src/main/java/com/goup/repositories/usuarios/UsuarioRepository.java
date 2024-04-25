package com.goup.repositories.usuarios;

import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u JOIN u.cargo c")
    List<Usuario> findAllWithJoin();

    List<Usuario> findAllByLoja(Loja loja);



}
