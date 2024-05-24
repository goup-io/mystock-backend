package com.goup.repositories.vendas;

import com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade;
import com.goup.entities.vendas.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer> {
    @Query("SELECT new com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade(pv.etp, pv.quantidade) FROM ProdutoVenda pv WHERE pv.venda.id = :id")
    List<RetornoETPeQuantidade> findAllEtpsByVendaId(Integer id);
}
