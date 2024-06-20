package com.goup.repositories.historicos;

import com.goup.entities.historicos.StatusHistoricoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusHistoricoProdRepository extends JpaRepository<StatusHistoricoProduto, Integer> {
    Optional<StatusHistoricoProduto> findByStatus(StatusHistoricoProduto.StatusHistorico statusHistoricoProduto);

}
