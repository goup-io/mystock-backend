package com.goup.repositories.historicos;

import com.goup.dtos.historico.transferencia.TransferenciaRes;
import com.goup.entities.historicos.StatusTransferencia;
import com.goup.entities.historicos.Transferencia;
import com.goup.entities.lojas.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

    @Query("SELECT t FROM Transferencia t WHERE t.coletor.loja = :loja")
    List<Transferencia> findAllByLoja(@Param("loja") Loja loja);

    @Query("SELECT hist FROM Transferencia hist JOIN hist.etp etp JOIN etp.produto produto " +
            "WHERE (:modelo IS NULL OR lower(produto.modelo.nome) LIKE lower(concat('%',:modelo, '%')))" +
            "AND (:produto IS NULL OR lower(produto.nome) LIKE lower(concat('%',:produto, '%')))" +
            "AND (:cor IS NULL OR lower(produto.cor.nome) LIKE lower(concat('%', :cor, '%')))" +
            "AND (:tamanho IS NULL OR etp.tamanho.numero = :tamanho) " +
            "AND (:id_loja IS NULL OR hist.coletor.loja.id = :id_loja) " +
            "AND (:status IS NULL OR hist.status.status = :status ) "+
            "AND (:dataInicio IS NULL OR hist.dataHora >= :dataInicio) " +
            "AND (:dataFim IS NULL OR hist.dataHora <= :dataFim) " +
            "AND (:pesquisa IS NULL OR lower(produto.nome) LIKE lower(concat('%', :pesquisa ,'%')) OR lower(produto.modelo.codigo) LIKE lower(concat('%', :pesquisa, '%')))"
    )
    List<Transferencia> findAllByFiltro(
        @Param("dataInicio") LocalDateTime dataInicio,
        @Param("dataFim") LocalDateTime dataFim,
        @Param("modelo") String modelo,
        @Param("produto") String produto,
        @Param("cor") String cor,
        @Param("tamanho") Integer tamanho,
        @Param("id_loja") Integer id_loja, // LOJA DE QUEM FEZ A SOLICITAÇÃO (coletor)
        @Param("status") StatusTransferencia.Status status,
        @Param("pesquisa") String pesquisa
    );

    @Query("SELECT hist FROM Transferencia hist JOIN hist.etp etp JOIN etp.produto produto " +
            "WHERE (:modelo IS NULL OR lower(produto.modelo.nome) LIKE lower(concat('%',:modelo, '%')))" +
            "AND (:produto IS NULL OR lower(produto.nome) LIKE lower(concat('%',:produto, '%')))" +
            "AND (:cor IS NULL OR lower(produto.cor.nome) LIKE lower(concat('%', :cor, '%')))" +
            "AND (:tamanho IS NULL OR etp.tamanho.numero = :tamanho) " +
            "AND (:id_loja IS NULL OR hist.etp.loja.id = :id_loja) " +
            "AND (:status IS NULL OR hist.status.status = :status ) "+
            "AND (:dataInicio IS NULL OR hist.dataHora >= :dataInicio) " +
            "AND (:dataFim IS NULL OR hist.dataHora <= :dataFim) " +
            "AND (:pesquisa IS NULL OR lower(produto.nome) LIKE lower(concat('%', :pesquisa ,'%')) OR lower(produto.modelo.codigo) LIKE lower(concat('%', :pesquisa, '%')))"
    )
    List<Transferencia> findAllByFiltroColetor(
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            @Param("modelo") String modelo,
            @Param("produto") String produto,
            @Param("cor") String cor,
            @Param("tamanho") Integer tamanho,
            @Param("id_loja") Integer id_loja, // LOJA DE QUEM FEZ A SOLICITAÇÃO (coletor)
            @Param("status") StatusTransferencia.Status status,
            @Param("pesquisa") String pesquisa
    );


    @Query("SELECT SUM(t.quantidadeLiberada) FROM Transferencia t JOIN t.etp e WHERE e.loja = :loja AND t.status.status = 'ACEITO'")
    Integer sumQuantidadeTransferidaByLoja(@Param("loja") Loja loja);

    @Query("SELECT SUM(t.quantidadeLiberada) FROM Transferencia t " +
            "JOIN t.etp e " +
            "WHERE e.loja = :loja AND t.status.status = 'ACEITO' AND MONTH(t.dataHora) = :month AND YEAR(t.dataHora) = :year")
    Integer sumQuantidadeTransferidaByLojaAndMonthAndAno(@Param("loja") Loja loja, @Param("month") Integer mes, @Param("year") Integer ano);

    @Query("SELECT SUM(t.quantidadeLiberada) FROM Transferencia t " +
            "JOIN t.etp e " +
            "WHERE t.status.status = 'ACEITO'" +
            "AND t.dataHora >= :dataInicial")
    Integer sumQuantidadeTransferidaByPeriod(@Param("dataInicial") LocalDateTime dataInicial);
    @Query("SELECT t FROM Transferencia t WHERE t.etp.loja.id = :idLoja ORDER BY t.dataHora DESC")
    List<Transferencia> findLastTransferenciaByLoja(@Param("idLoja") int idLoja);

}
