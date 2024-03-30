package com.goup.repositories;

import com.goup.entities.produtos.modelos.Modelo;
import com.goup.multiple_pk.ModeloPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, ModeloPK> {
    List<Modelo> findByNome(String nome);
    List<Modelo> findByCategoria_Nome(String categoria);


}
