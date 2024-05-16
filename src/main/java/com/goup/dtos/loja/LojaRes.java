package com.goup.dtos.loja;

import com.goup.dtos.loja.endereco.Endereco;
import com.goup.dtos.loja.endereco.EnderecoMapper;
import com.goup.dtos.loja.endereco.EnderecoRes;
import com.goup.services.ViacepService;

import java.io.IOException;

public record LojaRes(Integer id, String nome, String cnpj, String cep, Integer numero, String complemento){
    public EnderecoRes getEndereco(){
        ViacepService viaCep = new ViacepService();
        try{
            Endereco endereco = viaCep.getEndereco(this.cep());
            return EnderecoMapper.toDto(endereco);
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

