package com.goup.repositories.vendas;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findAllByUsuarioLojaIdAndStatusVendaStatus(Integer lojaId, StatusVenda.Status status);

    @Query("SELECT venda FROM Venda venda " +
            "LEFT JOIN ProdutoVenda produto ON produto.venda.id = venda.id " +
            "WHERE (:id_tipo_venda IS NULL OR venda.tipoVenda.id = :id_tipo_venda) " +
            "AND (:id_vendedor IS NULL OR venda.usuario.id = :id_vendedor)" +
            "AND (:data_inicio IS NULL OR :data_fim IS NULL OR venda.dataHora BETWEEN :data_inicio AND :data_fim)" +
            "AND (:id_loja IS NULL OR produto.etp.loja.id = :id_loja)" +
            "AND (:status IS NULL OR venda.statusVenda.status = :status)" +
            "GROUP BY venda"
    )
    List<Venda> findAllByFiltros(
            @Param("id_tipo_venda") Integer id_tipo_venda,
            @Param("id_vendedor") Integer id_vendedor,
            @Param("data_inicio") LocalDateTime dataHoraInicio,
            @Param("data_fim") LocalDateTime dataHoraInicio1,
            @Param("id_loja") Integer id_loja,
            @Param("status") StatusVenda.Status status
    );

    @Query("SELECT venda FROM Venda venda INNER JOIN venda.usuario usuario WHERE usuario.loja.id = 3")
    List <Venda> findAllByLoja(Integer id);
    
    @Query("SELECT COUNT(v.valorTotal) FROM Venda v " +
            "WHERE v.usuario.id = :usuarioId " +
            "AND MONTH(v.dataHora) = :mes " +
            "AND YEAR(v.dataHora) = :ano " +
            "AND v.statusVenda.status = 'FINALIZADA'")
    Integer countVendasByUsuario(@Param("usuarioId") Integer id_usuario, @Param("mes") Integer mes, @Param("ano") Integer ano);



//
//    @Query("SELECT SUM(pg.valor) FROM Venda v " +
//            "JOIN ProdutoVenda pv ON v.id = pv.venda.id " +
//            "JOIN pv.etp e " +
//            "JOIN e.produto p " +
//            "JOIN Pagamento pg ON pg.venda = v " +
//            "WHERE v.dataHora >= :startDate " +
//            "AND v.dataHora <= :endDate " +
//            "AND v.statusVenda.status = 'FINALIZADA'")
//    Double sumValorVendasPrecoRevendaBeteweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    @Query("SELECT SUM(p.valorCusto) FROM Venda v " +
            "JOIN ProdutoVenda pv ON v.id = pv.venda.id " +
            "JOIN pv.etp e " +
            "JOIN e.produto p " +
            "WHERE v.dataHora >= :startDate " +
            "AND v.statusVenda.status = 'FINALIZADA'")
    Double sumValorVendasPrecoCusto(@Param("startDate") LocalDateTime startDate);

    @Query("SELECT SUM(p.valorCusto) FROM Venda v " +
            "JOIN ProdutoVenda pv ON v.id = pv.venda.id " +
            "JOIN pv.etp e " +
            "JOIN e.produto p " +
            "WHERE v.dataHora >= :startDate " +
            "AND  v.dataHora <= :endDate " +
            "AND v.statusVenda.status = 'FINALIZADA'")
    Double sumValorVendasPrecoCustoBeteweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    @Query("SELECT SUM(p.valor)" +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "WHERE v.dataHora >= :startDate " +
            "AND  v.dataHora <= :endDate " +
            "AND v.statusVenda.status = 'FINALIZADA'")
    Double sumValorVendasPrecoRevendaBeteweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


    @Query("SELECT SUM(p.valor)" +
            "FROM Pagamento p " +
            "JOIN p.venda v " +
            "WHERE v.dataHora >= :startDate " +
            "AND v.statusVenda.status = 'FINALIZADA'")
    Double sumValorVendasPrecoRevenda(@Param("startDate") LocalDateTime startDate);

    List<Venda> findAllByUsuario_Id(Integer id);
}
