package com.goup.dtos.historico;

import com.goup.dtos.estoque.ETPTableRes;
import com.goup.dtos.estoque.produtos.ProdutoRes;

import java.time.LocalDateTime;

public record TransferenciaRes(
    Integer id,
    LocalDateTime dataHora,
    String loja_coletora,
    String loja_liberadora,
    ETPTableRes etp,
    Integer tamanho,
    Integer quantidadeSolicitada,
    Integer quantidadeLiberada,
    String coletor,
    String liberador
) {

}
