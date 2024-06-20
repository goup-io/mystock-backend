package com.goup.repositories.vendas;
import com.goup.dtos.dashboards.dashboardFuncionario.TotaisItensVendidosRes;
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
            "WHERE MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA' " +
            "AND v.usuario.loja.id = :lojaId " +
            "GROUP BY v.usuario.loja.id")
    Double sumValorTotalByMonthAndYearAndLoja(@Param("month") int month, @Param("year") int year, @Param("lojaId") Integer lojaId);

    @Query("SELECT SUM(v.valorTotal) FROM Pagamento p " +
            "JOIN p.venda v " +
            "WHERE MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA' " +
            "AND v.usuario.id = :usuarioId " +
            "GROUP BY v.usuario.id")
    Double sumValorTotalByMonthAndYearAndUsuario(@Param("month") int month, @Param("year") int year, @Param("usuarioId") Integer usuarioId);


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

    @Query("SELECT SUM(p.valor)" +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "WHERE v.usuario.id = :usuarioId " +
            "AND MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA'")
    Double sumPagamentosByUsuarioAndMonthAndYear(@Param("month") int month, @Param("year") int year, @Param("usuarioId") Integer usuarioId);

    @Query("SELECT SUM(pv.quantidade) " +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "JOIN ProdutoVenda pv on pv.venda = v " +
            "WHERE v.usuario.id = :usuarioId " +
            "AND MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "GROUP BY v.usuario.id")
    Integer sumTotalVendidos(@Param("month") int month, @Param("year") int year, @Param("usuarioId") Integer usuarioId);

    // soma por vendas naquele dia (faturamento diario) JOIN com entidade Venda para pegar a dataHora
    @Query("SELECT SUM(v.valorTotal) FROM Pagamento p JOIN p.venda v WHERE DAY(v.dataHora) = :day AND MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year AND v.statusVenda.status = 'FINALIZADA'")
    Double sumValorTotalByDayMonthAndYear(@Param("day") int day, @Param("month") int month, @Param("year") int year);

    // soma por vendas naquele dia (faturamento diario) JOIN com entidade Venda para pegar a dataHora
    @Query("SELECT SUM(v.valorTotal) " +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "WHERE DAY(v.dataHora) = :day " +
            "AND MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "AND v.usuario.loja.id = :lojaId " +
            "GROUP BY v.usuario.loja.id")
    Double sumValorTotalByDayMonthAndYearAndLoja(@Param("day") int day, @Param("month") int month, @Param("year") int year, @Param("lojaId") Integer lojaId);

    // soma por vendas naquele dia (faturamento diario) JOIN com entidade Venda para pegar a dataHora
    @Query("SELECT SUM(v.valorTotal) " +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "WHERE DAY(v.dataHora) = :day " +
            "AND MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "AND v.usuario.id = :usuarioId " +
            "GROUP BY v.usuario.id")
    Double sumValorTotalByDayMonthAndYearAndUsuario(@Param("day") int day, @Param("month") int month, @Param("year") int year, @Param("usuarioId") Integer usuarioId);


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


    @Query("SELECT new com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes(p.etp.produto.modelo, CAST(SUM(p.valorUnitario * p.quantidade) AS DOUBLE)) " +
            "FROM ProdutoVenda p " +
            "JOIN p.venda v " +
            "JOIN Pagamento pg ON pg.venda.id = v.id " +
            "WHERE MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "AND v.usuario.loja.id = :idLoja " +
            "GROUP BY p.etp.produto.modelo " +
            "ORDER BY SUM(p.valorUnitario * p.quantidade) DESC")
    List<ModeloEValorRes> findTop10ModelosByLojaIdMonthAndYear(@Param("idLoja") Integer idLoja,@Param("month") int month, @Param("year") int year);

    @Query("SELECT new com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes(p.etp.produto.modelo, CAST(SUM(p.valorUnitario * p.quantidade) AS DOUBLE)) " +
            "FROM ProdutoVenda p " +
            "JOIN p.venda v " +
            "JOIN Pagamento pg ON pg.venda.id = v.id " +
            "WHERE MONTH(v.dataHora) = :month " +
            "AND YEAR(v.dataHora) = :year " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "AND v.usuario.id = :usuarioId " +
            "GROUP BY p.etp.produto.modelo " +
            "ORDER BY SUM(p.valorUnitario * p.quantidade) DESC")
    List<ModeloEValorRes> findTop10ModelosByUsuarioIdMonthAndYear(@Param("usuarioId") Integer usuarioId, @Param("month") int monthValue, @Param("year") int year);

    @Query("SELECT new com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes(p.etp.produto.modelo, CAST(SUM(p.valorUnitario * p.quantidade) AS DOUBLE)) " +
            "FROM ProdutoVenda p " +
            "JOIN p.venda v " +
            "JOIN Pagamento pg ON pg.venda.id = v.id " +
            "WHERE v.dataHora >= :dataInicial " +
            "AND v.statusVenda.status = 'FINALIZADA'" +
            "GROUP BY p.etp.produto.modelo " +
            "ORDER BY SUM(p.valorUnitario * p.quantidade) DESC")
    List<ModeloEValorRes> findTop10ModelosByPeriod(@Param("dataInicial") LocalDateTime dataInicial);



    @Query("SELECT SUM(p.valor)" +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "JOIN v.usuario u " +
            "WHERE u.id = :usuarioId AND DAY(v.dataHora) = :day AND MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year AND v.statusVenda.status = 'FINALIZADA'")
    Double sumPagamentosByFuncionarioAndMonthAndYearAndDay(@Param("day") int day, @Param("month") int month, @Param("year") int year, @Param("usuarioId") Integer usuarioId);

}