package com.goup.repositories.produtos;

import com.goup.dtos.dashboards.dashboardGeral.FluxoEstoqueRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.lojas.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ETPRepository extends JpaRepository<ETP, Integer>{
    List<ETP> findAllByLoja_Id(Integer loja_id);
    Optional<ETP> findByTamanhoAndLojaAndProduto(Tamanho tamanho, Loja loja, Produto produto);

    @Query("SELECT etp FROM ETP etp JOIN etp.produto produto " +
            "WHERE (:modelo IS NULL OR lower(produto.modelo.nome) LIKE lower(concat('%',:modelo, '%'))) " +
            "AND (:cor IS NULL OR lower(produto.cor.nome) LIKE lower(concat('%',:cor,'%'))) " +
            "AND (:tamanho IS NULL OR etp.tamanho.numero = :tamanho) " +
            "AND (:precoMinimo IS NULL OR produto.valorRevenda >= :precoMinimo) " +
            "AND (:precoMaximo IS NULL OR produto.valorRevenda <= :precoMaximo) " +
            "AND (:id_loja IS NULL OR etp.loja.id = :id_loja)")
    List<ETP> findAllByFiltros(
        @Param("modelo") String modelo,
        @Param("cor") String cor,
        @Param("tamanho") Integer tamanho,
        @Param("precoMinimo") Double precoMinimo,
        @Param("precoMaximo") Double precoMaximo,
        @Param("id_loja") Integer id_loja
    );

    List<ETP> findAllByLoja(Loja loja);

    // contar quantidade de produtos em estoque
    @Query("SELECT COUNT(etp) FROM ETP etp WHERE etp.loja = :loja")
    Integer countByLoja(Loja loja);

    // contar quantidade de produtos em estoque
    @Query("SELECT sum(etp.quantidade) FROM ETP etp")
    Integer sumETP_Quantidade();

    @Query("SELECT SUM(e.quantidade) FROM ETP e WHERE e.loja = :loja")
    Integer sumETP_QuantidadeByLoja(@Param("loja") Loja loja);


//    @Query("SELECT " +
//            "new com.goup.dtos.dashboards.dashboardGeral.FluxoEstoqueRes(" +
//            "e.loja.nome," +
//            "SUM(e.quantidade)," +
//            "SUM(CASE WHEN v.statusVenda.status = 'FINALIZADA' THEN pv.quantidade ELSE 0 END)," +
//            "SUM(CASE WHEN t.status.status = 'ACEITO' THEN t.quantidadeLiberada ELSE 0 END))" +
//            "FROM ETP e " +
//            "LEFT JOIN ProdutoVenda pv ON pv.etp.id = e.id " +
//            "LEFT JOIN pv.venda v " +
//            "LEFT JOIN Pagamento pg ON pg.venda.id = v.id " +
//            "LEFT JOIN Transferencia t ON t.etp.id = e.id " +
//            "GROUP BY e.loja.nome")
//    List<FluxoEstoqueRes> sumFluxoEstoqueByLoja();




}
