package com.goup.repositories;

import com.goup.entities.produtos.modelos.Modelo;
import com.goup.multiple_pk.ModeloPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, ModeloPK> {
}
