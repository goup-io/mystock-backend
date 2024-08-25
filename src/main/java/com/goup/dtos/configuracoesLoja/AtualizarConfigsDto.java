package com.goup.dtos.configuracoesLoja;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AtualizarConfigsDto(
        @NotNull
        Integer idLoja,
        @NotNull @DecimalMin("1.0")
        Integer limiteEstoqueAlerta) { }
