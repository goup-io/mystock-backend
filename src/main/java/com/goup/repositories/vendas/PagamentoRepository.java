package com.goup.repositories.vendas;
import com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes;
import com.goup.entities.vendas.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    // soma por vendas naquele mês (faturamento mensal) JOIN com entidade Venda para pegar a dataHora
    @Query("SELECT SUM(v.valorTotal) FROM Pagamento p JOIN p.venda v WHERE MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year AND v.statusVenda.status = 'FINALIZADA'")
    Double sumValorTotalByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(v.valorTotal) FROM Pagamento p " +
            "JOIN p.venda v " +
            "JOIN ProdutoVenda pv ON pv.venda.id = v.id " +
            "JOIN pv.etp e " +
            "WHERE MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "AND e.loja.id = :lojaId " +
            "GROUP BY v.id")
    Double sumValorTotalByMonthAndYearAndLoja(@Param("month") int month, @Param("year") int year, @Param("lojaId") Integer lojaId);

    @Query("SELECT SUM(p.valor)" +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "JOIN v.usuario u " +
            "WHERE u.loja.id = :lojaId AND MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year AND v.statusVenda.status = 'FINALIZADA'")
    Double sumPagamentosByLojaAndMonthAndYear(@Param("month") int month, @Param("year") int year, @Param("lojaId") Integer lojaId);

    @Query("SELECT SUM(p.valor)" +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "JOIN v.usuario u " +
            "WHERE u.loja.id = :lojaId AND DAY(v.dataHora) = :day AND MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year AND v.statusVenda.status = 'FINALIZADA'")
    Double sumPagamentosByLojaAndMonthAndYearAndDay(@Param("day") int day, @Param("month") int month, @Param("year") int year, @Param("lojaId") Integer lojaId);

    // soma por vendas naquele dia (faturamento diario) JOIN com entidade Venda para pegar a dataHora
    @Query("SELECT SUM(v.valorTotal) FROM Pagamento p JOIN p.venda v WHERE DAY(v.dataHora) = :day AND MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year AND v.statusVenda.status = 'FINALIZADA'")
    Double sumValorTotalByDayMonthAndYear(@Param("day") int day, @Param("month") int month, @Param("year") int year);

    // soma por vendas naquele dia (faturamento diario) JOIN com entidade Venda para pegar a dataHora
    @Query("SELECT SUM(v.valorTotal) " +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "JOIN ProdutoVenda pv ON pv.venda.id = v.id " +
            "JOIN pv.etp e " +
            "WHERE DAY(v.dataHora) = :day " +
            "AND MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "AND e.loja.id = :lojaId " +
            "GROUP BY v.id")
    Double sumValorTotalByDayMonthAndYearAndLoja(@Param("day") int day, @Param("month") int month, @Param("year") int year, @Param("lojaId") Integer lojaId);

    // pagamentos por venda
    @Query("SELECT SUM(p.valor) FROM Pagamento p WHERE p.venda.id = :id")
    Double sumValorPago(Integer id);

    // Fluxo pagamento
    List<Pagamento> findAllByVenda_Id (Integer id);

    @Query("SELECT new com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes(p.etp.produto.modelo, CAST(SUM(p.valorUnitario * p.quantidade) AS DOUBLE)) " +
            "FROM ProdutoVenda p " +
            "JOIN p.venda v " +
            "JOIN Pagamento pg ON pg.venda.id = v.id " +
            "WHERE MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year AND v.statusVenda.status = 'FINALIZADA'" +
            "GROUP BY p.etp.produto.modelo " +
            "ORDER BY SUM(p.valorUnitario * p.quantidade) DESC")
    List<ModeloEValorRes> findTop10ModelosByMonthAndYear(@Param("month") int month, @Param("year") int year);

}