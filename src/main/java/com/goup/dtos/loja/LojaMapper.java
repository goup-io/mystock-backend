package com.goup.dtos.loja;

import com.goup.entities.lojas.Loja;

import java.util.List;

public class LojaMapper {
    public static LojaResponseDto toResponse(Loja loja){
        return new LojaResponseDto(
                loja.getId(),
                loja.getNome()
        );
    }

    public static List<LojaResponseDto> toResponse(List<Loja> lojas){
        return lojas.stream()
                .map(LojaMapper::toResponse)
                .toList();
    }
}
