package com.goup.dtos.estoque;

import com.goup.entities.estoque.ETP;

import java.time.LocalDateTime;

public record Notificacao(
    LocalDateTime dataHora,
    String descricao
) {
}
