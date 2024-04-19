package com.goup.dtos.loja;

import com.goup.entities.lojas.TipoLogin;

public record LojaLoginResponseDTO (String token, Integer idLoja, TipoLogin tipoLogin){
}
