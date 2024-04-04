package com.goup.repositories;

import com.goup.entities.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /*
    List<Usuario> findAll();
    Optional<Usuario> findById(int id);
    Usuario update(Usuario usuario);
    Usuario delete(Usuario usuario);

     */
}
