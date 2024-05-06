package com.goup.repositories.usuarios;

import com.goup.entities.usuarios.login.RedefinirSenha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RedefinirSenhaRepository extends JpaRepository<RedefinirSenha, Integer> {
    Optional<RedefinirSenha> findByToken(String token);
}
