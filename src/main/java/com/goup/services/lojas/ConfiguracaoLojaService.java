package com.goup.services.lojas;

import com.goup.dtos.configuracoesLoja.AtualizarConfigsDto;
import com.goup.dtos.historico.produto.HistoricoProdutoMapper;
import com.goup.entities.historicos.HistoricoProduto;
import com.goup.entities.lojas.ConfiguracaoLoja;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.lojas.ConfiguracaoLojaRepository;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.AlertasEstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfiguracaoLojaService {
    private final ConfiguracaoLojaRepository configuracaoLojaRepository;

    public ConfiguracaoLojaService(
            ConfiguracaoLojaRepository configuracaoLojaRepository,
            LojaRepository lojaRepository) {
        this.configuracaoLojaRepository = configuracaoLojaRepository;
    }

    public ConfiguracaoLoja buscarConfiguracoes(Integer idLoja){
        ConfiguracaoLoja configuracaoLoja = configuracaoLojaRepository.findConfiguracaoLojaByLoja_Id(idLoja)
                .orElseThrow(() -> new RegistroNaoEncontradoException("ConfiguracaoLoja não encontrado com o idLoja: " + idLoja));
        return configuracaoLoja;
    }

    public ConfiguracaoLoja atualizarConfiguracoes(AtualizarConfigsDto atualizarConfigsDto) {
        ConfiguracaoLoja configuracaoLoja = configuracaoLojaRepository.findConfiguracaoLojaByLoja_Id(atualizarConfigsDto.idLoja())
                .orElseThrow(() -> new RegistroNaoEncontradoException("ConfiguracaoLoja não encontrado com o idLoja: "+ atualizarConfigsDto.idLoja()));
        configuracaoLoja.setLimiteEstoqueAlerta(atualizarConfigsDto.limiteEstoqueAlerta());
        return configuracaoLojaRepository.save(configuracaoLoja);
    }
}
