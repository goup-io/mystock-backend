package com.goup.repositories.historicos;

import com.goup.entities.historicos.HistoricoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoProdutoRepository extends JpaRepository<HistoricoProduto, Integer> {

    List<HistoricoProduto> findByProdutoVenda_id(Integer id);
}
