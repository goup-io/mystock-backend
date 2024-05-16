package com.goup.dtos.historico;

import com.goup.entities.usuarios.Usuario;

public record TransferenciaReqAprovar (
    Integer id_liberador,
    Integer quantidadeLiberada
) {
}
