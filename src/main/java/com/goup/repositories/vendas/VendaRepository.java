package com.goup.repositories.vendas;

import com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade;
import com.goup.entities.estoque.ETP;
import com.goup.entities.vendas.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    @Query("SELECT new com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade(pv.etp, pv.quantidade) FROM ProdutoVenda pv WHERE pv.venda.id = :id")
    List<RetornoETPeQuantidade> findAllEtpsByVendaId(Integer id);
}
