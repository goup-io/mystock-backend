package com.goup.repositories.usuarios;

import com.goup.entities.usuarios.login.RedefinirSenha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedefinirSenhaRepository extends JpaRepository<RedefinirSenha, Integer> {
}
