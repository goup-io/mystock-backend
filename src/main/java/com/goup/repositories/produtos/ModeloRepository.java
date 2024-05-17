package com.goup.repositories.produtos;

import com.goup.entities.estoque.produtos.modelos.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
    List<Modelo> findAllByNomeIgnoreCase(String nome);
    List<Modelo> findAllByCategoria_NomeIgnoreCase(String categoria);

    Optional<Modelo> findByCodigo(String codigo);

    boolean existsById(int id);
    void deleteByCodigo(String codigo);
}
