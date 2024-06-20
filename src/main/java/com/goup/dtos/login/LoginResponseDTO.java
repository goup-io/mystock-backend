package com.goup.dtos.login;


public record LoginResponseDTO(String token, Integer idUser, String contexto, String cargo, Integer idLoja){
}
