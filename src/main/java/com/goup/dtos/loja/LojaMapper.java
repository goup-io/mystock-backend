package com.goup.dtos.loja;

import com.goup.entities.lojas.Loja;

import java.util.List;

public class LojaMapper {
    public static Loja reqToEntity(LojaReq dto){
        Loja entidade = new Loja();
        entidade.setNome(dto.nome());
        entidade.setCnpj(dto.cnpj());
        entidade.setCep(dto.cep());
        entidade.setNumero(dto.numero());
        entidade.setComplemento(dto.complemento());
        return entidade;
    }

    public static LojaRes toDto(Loja entidade){
        int id = entidade.getId();
        String nome = entidade.getNome();
        String cnpj = entidade.getCnpj();
        String cep = entidade.getCep();
        Integer numero = entidade.getNumero();
        String complemento = entidade.getComplemento();
        LojaRes dto = new LojaRes(id, nome, cnpj, cep, numero, complemento);
        return dto;
    }

    public static List<LojaRes> toDto(List<Loja> lista){
        return lista.stream().map(LojaMapper::toDto).toList();
    }

    public static Loja updateEntity(Loja loja, LojaReq atualizacao){
        loja.setNome(atualizacao.nome());
        loja.setCnpj(atualizacao.cnpj());
        loja.setCep(atualizacao.cep());
        loja.setNumero(atualizacao.numero());
        loja.setComplemento(atualizacao.complemento());
        return loja;
    }
}
