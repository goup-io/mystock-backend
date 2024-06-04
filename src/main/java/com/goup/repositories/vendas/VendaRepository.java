package com.goup.repositories.vendas;

import com.goup.dtos.vendas.produtoVenda.RetornoETPeQuantidade;
import com.goup.entities.estoque.ETP;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    @Query("SELECT venda FROM Venda venda " +
            "WHERE (:tipo_venda IS NULL OR venda.tipoVenda = :tipo_venda) " +
            "AND (:id_vendedor IS NULL OR venda.usuario.id = :id_vendedor)" +
            "AND (venda.dataHora BETWEEN :data_inicio AND :data_fim)" +
            "AND (:id_loja IS NULL OR venda.usuario.loja.id = :id_loja)"
    )
    List<Venda> findAllByFiltros(
            @Param("tipo_venda") TipoVenda tipoVenda,
            @Param("id_vendedor") Integer id_vendedor,
            @Param("data_inicio") LocalDateTime dataHoraInicio,
            @Param("data_fim") LocalDateTime dataHoraInicio1,
            @Param("id_loja") Integer id_loja
    );
}
