package com.goup.dtos.historico.transferencia;

import java.util.List;

public record TransferenciaReq(
        Integer coletor_id,
        List<TransferenciaETPEQuantidade> itens
) {
}
