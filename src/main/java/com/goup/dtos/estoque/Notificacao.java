package com.goup.dtos.estoque;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goup.entities.estoque.ETP;

import java.time.LocalDateTime;

public record Notificacao(
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataHora,
    String descricao
) {
}
