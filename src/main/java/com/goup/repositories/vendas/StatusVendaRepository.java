package com.goup.repositories.vendas;

import com.goup.entities.vendas.StatusVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusVendaRepository extends JpaRepository<StatusVenda, Integer> {
    Optional<StatusVenda> findByStatus(StatusVenda.Status status);
}
