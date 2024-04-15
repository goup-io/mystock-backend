package com.goup.repositories.produtos;

import com.goup.entities.produtos.modelos.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
    List<Modelo> findAllByNomeIgnoreCase(String nome);
    List<Modelo> findAllByCategoria_NomeIgnoreCase(String categoria);



}
