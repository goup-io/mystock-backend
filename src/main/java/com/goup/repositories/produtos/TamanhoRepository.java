package com.goup.repositories.produtos;

import com.goup.entities.estoque.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TamanhoRepository extends JpaRepository<Tamanho, Integer> {
}
