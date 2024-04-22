package com.goup.repositories.usuarios;

import com.goup.entities.usuarios.login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    UserDetails findByUsername(String username);
}
