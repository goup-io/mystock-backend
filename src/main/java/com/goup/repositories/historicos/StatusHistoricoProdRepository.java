package com.goup.repositories.historicos;

import com.goup.entities.historicos.StatusHistoricoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusHistoricoProdRepository extends JpaRepository<StatusHistoricoProduto, Integer> {
}
