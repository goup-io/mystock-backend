package com.goup.repositories;

import com.goup.entities.produtos.modelos.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
