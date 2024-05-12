package com.goup.repositories.historicos;

import com.goup.entities.historicos.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

    @Query("SELECT hist FROM Transferencia hist JOIN hist.etp etp JOIN etp.produto produto " +
            "WHERE (:modelo IS NULL OR lower(produto.modelo.nome) LIKE lower(concat('%',:modelo, '%')))" +
            "AND (:cor IS NULL OR lower(produto.cor.nome) LIKE lower(concat('%', :cor, '%')))" +
            "AND (:tamanho IS NULL OR etp.tamanho.numero = :tamanho) " +
            "AND (:dataInicio IS NULL OR hist.dataHora >= :dataInicio)" +
            "AND (:dataFim IS NULL OR hist.dataHora <= :dataFim)"
    )
    List<Transferencia> findAllByFiltro(
        @Param("dataInicio") LocalDateTime dataInicio,
        @Param("dataFim") LocalDateTime dataFim,
        @Param("modelo") String modelo,
        @Param("cor") String cor,
        @Param("tamanho") Integer tamanho
    );
}
