package com.goup.repositories.produtos;

import com.goup.entities.produtos.modelos.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
