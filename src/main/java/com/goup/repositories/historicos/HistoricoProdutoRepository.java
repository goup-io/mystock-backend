package com.goup.repositories.historicos;

import com.goup.entities.historicos.HistoricoProduto;
import com.goup.entities.vendas.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoricoProdutoRepository extends JpaRepository<HistoricoProduto, Integer> {

    Optional<HistoricoProduto> findByProdutoVenda_id(Integer id);
}
