package com.goup.repositories.vendas;

import com.goup.entities.estoque.ETP;
import com.goup.entities.vendas.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    // soma por vendas naquele mês (faturamento mensal) JOIN com entidade Venda para pegar a dataHora
    @Query("SELECT SUM(v.valorTotal) FROM Pagamento p JOIN p.venda v WHERE MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year")
    Double sumValorTotalByMonthAndYear(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(v.valorTotal) FROM Pagamento p JOIN p.venda v JOIN ProdutoVenda pv ON pv.venda = v JOIN pv.etp e JOIN e.loja l WHERE pv.etp.loja.id = :idLoja AND MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year")
    Double sumValorTotalByMonthAndYearAndLoja(@Param("month") int month, @Param("year") int year, @Param("idLoja") Integer idLoja);

    // soma por vendas naquele dia (faturamento diario) JOIN com entidade Venda para pegar a dataHora
    @Query("SELECT SUM(v.valorTotal) FROM Pagamento p JOIN p.venda v WHERE DAY(v.dataHora) = :day AND MONTH(v.dataHora) = :month AND YEAR(v.dataHora) = :year")
    Double sumValorTotalByDayMonthAndYear(@Param("day") int day, @Param("month") int month, @Param("year") int year);

    // pagamentos por venda
    @Query("SELECT SUM(p.valor) FROM Pagamento p WHERE p.venda.id = :id")
    Double sumValorPago(Integer id);
}

