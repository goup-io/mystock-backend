package com.goup.services.alertas;

import com.goup.entities.estoque.AlertasEstoque;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.lojas.ConfiguracaoLojaRepository;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.AlertasEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertasService {

    private final AlertasEstoqueRepository AlertasEstoqueRepository;
    private final ConfiguracaoLojaRepository configuracaoLojaRepository;
    private final LojaRepository lojaRepository;

    public AlertasService(
            AlertasEstoqueRepository alertasEstoqueRepository,
            ConfiguracaoLojaRepository configuracaoLojaRepository,
            LojaRepository lojaRepository) {
        this.AlertasEstoqueRepository = alertasEstoqueRepository;
        this.configuracaoLojaRepository = configuracaoLojaRepository;
        this.lojaRepository = lojaRepository;
    }

    public Integer buscarLimiteAlertasLoja(Integer idLoja){
        Boolean loja = lojaRepository.existsById(idLoja);
        if (!loja){
            throw new RegistroNaoEncontradoException("Loja não econtrada - ID INVÁLIDO");
        }

        Integer limiteEstoqueAlertaByLojaId = configuracaoLojaRepository.findLimiteEstoqueAlertaByLoja_Id(idLoja);
        if (limiteEstoqueAlertaByLojaId == null){
            return 25;
        }
        return limiteEstoqueAlertaByLojaId;
    }


}
