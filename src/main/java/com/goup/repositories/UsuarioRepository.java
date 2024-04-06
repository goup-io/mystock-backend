package com.goup.repositories;

import com.goup.entities.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u JOIN u.cargo c")
    List<Usuario> findAllWithJoin();


    /*
    List<Usuario> findAll();
    Optional<Usuario> findById(int id);
    Usuario update(Usuario usuario);
    Usuario delete(Usuario usuario);

     */
}
