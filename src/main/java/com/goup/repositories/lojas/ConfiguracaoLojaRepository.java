package com.goup.repositories.lojas;

import com.goup.entities.lojas.ConfiguracaoLoja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConfiguracaoLojaRepository extends JpaRepository<ConfiguracaoLoja, Integer> {

    @Query("SELECT c.limiteEstoqueAlerta FROM ConfiguracaoLoja c WHERE c.loja.id = :idLoja")
    Integer findLimiteEstoqueAlertaByLoja_Id(@Param("idLoja") Integer idLoja);

    @Query("SELECT c FROM ConfiguracaoLoja c WHERE c.loja.id = :idLoja")
    Optional<ConfiguracaoLoja> findConfiguracaoLojaByLoja_Id(@Param("idLoja") Integer idLoja);
}
