package com.goup.repositories.vendas;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findAllByUsuarioLojaIdAndStatusVendaStatus(Integer lojaId, StatusVenda.Status status);

    @Query("SELECT venda FROM Venda venda " +
            "WHERE (:tipo_venda IS NULL OR venda.tipoVenda = :tipo_venda) " +
            "AND (:id_vendedor IS NULL OR venda.usuario.id = :id_vendedor)" +
            "AND (:data_inicio IS NULL OR :data_fim IS NULL OR venda.dataHora BETWEEN :data_inicio AND :data_fim)" +
            "AND (:id_loja IS NULL OR venda.usuario.loja.id = :id_loja)"
    )
    List<Venda> findAllByFiltros(
            @Param("tipo_venda") TipoVenda tipoVenda,
            @Param("id_vendedor") Integer id_vendedor,
            @Param("data_inicio") LocalDateTime dataHoraInicio,
            @Param("data_fim") LocalDateTime dataHoraInicio1,
            @Param("id_loja") Integer id_loja
    );

    @Query("SELECT venda FROM Venda venda INNER JOIN venda.usuario usuario WHERE usuario.loja.id = 3")
    List <Venda> findAllByLoja(Integer id);
}
