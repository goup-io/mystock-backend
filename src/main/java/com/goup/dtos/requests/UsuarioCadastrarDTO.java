package com.goup.dtos.requests;


public record UsuarioCadastrarDTO(Integer codigoVenda, String nome, Integer idCargo, String email, String telefone, Integer idLoja) {
}
