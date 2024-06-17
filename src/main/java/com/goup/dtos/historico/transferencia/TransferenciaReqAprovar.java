package com.goup.dtos.historico.transferencia;

import com.goup.entities.usuarios.Usuario;

public record TransferenciaReqAprovar (
    Integer cod_liberador,
    Integer quantidadeLiberada
) {
}
