package com.goup.repositories.produtos;

import com.goup.entities.estoque.Tamanho;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TamanhoRepository extends JpaRepository<Tamanho, Integer> {
    Optional<Tamanho> findByNumero(Integer numero);
    boolean existsByNumero(Integer numero);
    @Transactional
    void deleteByNumero(Integer numero);
}
