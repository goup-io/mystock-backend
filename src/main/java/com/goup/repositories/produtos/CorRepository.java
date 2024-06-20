package com.goup.repositories.produtos;

import com.goup.entities.estoque.produtos.Cor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorRepository extends JpaRepository<Cor, Integer> {
    boolean existsById(int id);
    boolean existsByNome(String nome);
}
