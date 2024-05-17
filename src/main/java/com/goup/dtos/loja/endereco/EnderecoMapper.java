package com.goup.dtos.loja.endereco;

public class EnderecoMapper {
    public static EnderecoRes toDto(Endereco e){
        EnderecoRes dto = new EnderecoRes(e.logradouro(), e.complemento(), e.bairro(), e.uf(), e.ibge(), e.gia(), e.ddd(), e.siafi());
        return dto;
    }
}
