package com.goup.repositories.vendas;

import com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade;
import com.goup.entities.estoque.ETP;
import com.goup.entities.lojas.Loja;
import com.goup.entities.vendas.ProdutoVenda;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Integer> {
    @Query("SELECT new com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade(pv.etp, pv.quantidade) FROM ProdutoVenda pv WHERE pv.venda.id = :id")
    List<RetornoETPeQuantidade> findAllEtpsByVendaId(Integer id);

    @Query("SELECT pv FROM ProdutoVenda pv WHERE pv.venda.id = :id")
    List<ProdutoVenda> findAllProdutoVendaIdVenda(Integer id);

    @Query("SELECT pv.etp FROM ProdutoVenda pv " +
            "JOIN pv.venda v " +
            "JOIN Pagamento p ON p.venda = v " +
            "WHERE MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year AND v.statusVenda.status = 'FINALIZADA' " +
            "GROUP BY pv.etp " +
            "ORDER BY SUM(p.valor) DESC")
    Page<ETP> findTopETPByMonthAndYear(@Param("month") int month, @Param("year") int year, Pageable pageable);

    @Query("SELECT SUM(pv.quantidade) FROM ProdutoVenda pv JOIN pv.etp e WHERE e.loja = :loja AND pv.venda.statusVenda.status = 'FINALIZADA'")
    Integer sumQuantidadeVendidaByLoja(@Param("loja") Loja loja);

    @Query("SELECT SUM(pv.quantidade) FROM ProdutoVenda pv " +
            "JOIN pv.etp e " +
            "WHERE e.loja = :loja AND pv.venda.statusVenda.status = 'FINALIZADA' " +
            "AND MONTH(pv.venda.dataHora) = :month AND YEAR(pv.venda.dataHora) = :year")
    Integer sumQuantidadeVendidaByLojaAndMesAndAno(@Param("loja") Loja loja, @Param("month") Integer mes, @Param("year") Integer ano);
}
