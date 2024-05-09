package com.goup.repositories.usuarios;

import com.goup.entities.usuarios.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    UserDetails findByUsername(String username);

    @Query("SELECT l FROM Login l JOIN l.usuario u WHERE u.email = :email")
    Login findLoginByEmail(String email);

    @Query("SELECT l FROM Login l JOIN l.usuario u WHERE u.id = :id")
    Login findLoginById(Integer id);

}




