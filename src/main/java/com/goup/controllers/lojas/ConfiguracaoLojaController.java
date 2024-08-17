package com.goup.controllers.lojas;

import com.goup.dtos.configuracoesLoja.AtualizarConfigsDto;
import com.goup.entities.lojas.ConfiguracaoLoja;
import com.goup.services.lojas.ConfiguracaoLojaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuracao-lojas")
public class ConfiguracaoLojaController {

    private final ConfiguracaoLojaService configuracaoLojaService;

    public ConfiguracaoLojaController(ConfiguracaoLojaService configuracaoLojaService) {
        this.configuracaoLojaService = configuracaoLojaService;
    }

    @GetMapping("/{idLoja}")
    public ResponseEntity<ConfiguracaoLoja> buscarConfiguracoes(@PathVariable Integer idLoja) {
        ConfiguracaoLoja configuracaoLoja = configuracaoLojaService.buscarConfiguracoes(idLoja);
        return ResponseEntity.status(200).body(configuracaoLoja);
    }

    @PutMapping
    public ResponseEntity<ConfiguracaoLoja> atualizarConfiguracoes(@RequestBody AtualizarConfigsDto atualizarConfigsDto){
        return ResponseEntity.status(201).body(configuracaoLojaService.atualizarConfiguracoes(atualizarConfigsDto));
    }
}
