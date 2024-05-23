package com.goup.repositories.vendas;

import com.goup.entities.vendas.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer> {
}
