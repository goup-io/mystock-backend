package com.goup.repositories.usuarios;

import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u JOIN u.cargo c")
    List<Usuario> findAllWithJoin();

    List<Usuario> findAllByLoja(Loja loja);
    List<Usuario> findAllByLoja_Id(int id_loja);


    @Query("SELECT MAX(u.id) FROM Usuario u")
    Integer findMaxId();

    Optional<Usuario> findByCodigoVenda(Integer codigoVenda);

}
