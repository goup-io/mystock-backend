package com.goup.dtos.historico.transferencia;

import java.util.List;

public record TransferenciaReq(
        Integer coletor_cod,
        List<TransferenciaETPEQuantidade> itens
) {
}
