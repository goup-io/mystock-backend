package com.goup.repositories.produtos;

import com.goup.entities.estoque.produtos.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
