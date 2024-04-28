package com.goup.dtos.loja;

import com.goup.services.ViacepService;

import java.io.IOException;

public record LojaRes(Integer id, String nome, String cnpj, String cep, Integer numero, String complemento){
    public Endereco getEndereco(){
        ViacepService viaCep = new ViacepService();
        try{
            Endereco endereco = viaCep.getEndereco(this.cep());
            return endereco;
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

