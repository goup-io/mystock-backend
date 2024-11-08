package com.goup.dtos.historico.transferencia;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goup.dtos.estoque.ETPTableRes;
import com.goup.dtos.estoque.produtos.ProdutoRes;
import com.goup.entities.historicos.StatusTransferencia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record TransferenciaRes(
    Integer id,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataHora,
    StatusTransferencia status,
    String loja_coletora,
    String loja_liberadora,
    ETPTableRes etp,
    Integer tamanho,
    Integer quantidadeSolicitada,
    Integer quantidadeLiberada,
    String coletor,
    String liberador
) {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
}
