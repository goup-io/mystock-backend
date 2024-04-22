package com.goup.repositories.lojas;

import com.goup.entities.lojas.LojaLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginLojaRepository extends JpaRepository<LojaLogin, Integer> {
    UserDetails findByUsername(String username);
}
