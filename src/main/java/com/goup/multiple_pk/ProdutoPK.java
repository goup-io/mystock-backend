package com.goup.multiple_pk;

import com.goup.entities.produtos.Cor;
import com.goup.entities.produtos.modelos.Modelo;

import java.io.Serializable;

public class ProdutoPK implements Serializable {
    private int id;
    private Cor cor;
    private Modelo modelo;
}
