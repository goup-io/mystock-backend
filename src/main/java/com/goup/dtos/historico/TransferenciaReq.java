package com.goup.dtos.historico;

public record TransferenciaReq(
    Integer quantidadeSolicitada,
    Integer coletor_id,
    Integer etp_id
) {
}
