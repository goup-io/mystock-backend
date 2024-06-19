package com.goup.repositories.produtos;

import com.goup.entities.estoque.AlertasEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlertasEstoqueRepository extends JpaRepository<AlertasEstoque, Integer> {
    List<AlertasEstoque> findAllByEtpLoja_Id(int id_loja);


    @Query("SELECT a FROM AlertasEstoque a " +
            "WHERE (:dataInicio IS NULL OR a.dataHora >= :dataInicio) " +
            "AND (:dataFim IS NULL OR a.dataHora <= :dataFim) " +
            "AND (:id_loja IS NULL OR a.etp.loja.id = :id_loja)")
    List<AlertasEstoque> findAllByFiltro(
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            @Param("id_loja") Integer id_loja
    );

    @Query("SELECT ae FROM AlertasEstoque ae WHERE ae.etp.loja.id = :idLoja ORDER BY ae.dataHora DESC")
    List<AlertasEstoque> findLastAlertByLoja(@Param("idLoja") int idLoja);

}
