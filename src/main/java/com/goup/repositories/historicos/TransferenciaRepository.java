package com.goup.repositories.historicos;

import com.goup.entities.historicos.StatusTransferencia;
import com.goup.entities.historicos.Transferencia;
import com.goup.entities.lojas.Loja;
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
            "AND (:id_loja IS NULL OR hist.coletor.loja.id = :id_loja)" +
            "AND (:status IS NULL OR hist.status.status = :status )"+
            "AND (:dataInicio IS NULL OR hist.dataHora >= :dataInicio)" +
            "AND (:dataFim IS NULL OR hist.dataHora <= :dataFim)"
    )
    List<Transferencia> findAllByFiltro(
        @Param("dataInicio") LocalDateTime dataInicio,
        @Param("dataFim") LocalDateTime dataFim,
        @Param("modelo") String modelo,
        @Param("cor") String cor,
        @Param("tamanho") Integer tamanho,
        @Param("id_loja") Integer id_loja, // LOJA DE QUEM FEZ A SOLICITAÇÃO (coletor)
        @Param("status") StatusTransferencia.Status status
    );

    @Query("SELECT SUM(t.quantidadeLiberada) FROM Transferencia t JOIN t.etp e WHERE e.loja = :loja AND t.status.status = 'ACEITO'")
    Integer sumQuantidadeTransferidaByLoja(@Param("loja") Loja loja);

    @Query("SELECT SUM(t.quantidadeLiberada) FROM Transferencia t " +
            "JOIN t.etp e " +
            "WHERE e.loja = :loja AND t.status.status = 'ACEITO' AND MONTH(t.dataHora) = :month AND YEAR(t.dataHora) = :year")
    Integer sumQuantidadeTransferidaByLojaAndMonthAndAno(@Param("loja") Loja loja, @Param("month") Integer mes, @Param("year") Integer ano);
}
