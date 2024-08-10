package com.goup.repositories.usuarios;

import com.goup.entities.usuarios.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    UserDetails findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("SELECT l FROM Login l JOIN l.usuario u WHERE u.email = :email")
    Login findLoginByEmail(String email);

    @Query("SELECT l FROM Login l JOIN l.usuario u WHERE u.id = :id")
    Login findLoginByIdUsuario(Integer id);

}




