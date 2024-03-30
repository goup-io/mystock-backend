package com.goup.repositories;

import com.goup.entities.produtos.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TamanhoRepository extends JpaRepository<Tamanho, Integer> {
}
