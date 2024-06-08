package com.goup.dtos.historico.transferencia;

public record TransferenciaReq(
    Integer quantidadeSolicitada,
    Integer coletor_id,
    Integer etp_id
) {
}
