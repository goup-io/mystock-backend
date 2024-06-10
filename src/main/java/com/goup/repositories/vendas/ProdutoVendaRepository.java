package com.goup.repositories.vendas;

import com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade;
import com.goup.entities.estoque.ETP;
import com.goup.entities.vendas.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer> {
    @Query("SELECT new com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade(pv.etp, pv.quantidade) FROM ProdutoVenda pv WHERE pv.venda.id = :id")
    List<RetornoETPeQuantidade> findAllEtpsByVendaId(Integer id);

    @Query("SELECT pv FROM ProdutoVenda pv WHERE pv.venda.id = :id")
    List<ProdutoVenda> findAllProdutoVendaIdVenda(Integer id);

    @Query("SELECT pv.etp FROM ProdutoVenda pv JOIN pv.venda v WHERE MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year GROUP BY pv.etp ORDER BY COUNT(pv) DESC")
    Optional<ETP> findTopETPByMonthAndYear(@Param("month") int month, @Param("year") int year);

}
