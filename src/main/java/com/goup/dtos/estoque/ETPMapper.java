package com.goup.dtos.estoque;

import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.lojas.Loja;
import com.goup.entities.vendas.ItemPromocional;
import com.goup.utils.ListaGenerica;

import java.util.ArrayList;
import java.util.List;

public class ETPMapper {
    public static ETPTableRes toTableResponseEntity(ETP req) {
    return new ETPTableRes(
            req.getId(),
            req.getCodigo(),
            req.getProduto().getNome(),
            req.getProduto().getModelo().getNome(),
            req.getTamanho().getNumero(),
            req.getProduto().getCor().getNome(),
            req.getProduto().getValorRevenda(),
            req.getLoja().getNome(),
            req.getItemPromocional(),
            req.getQuantidade(),
            req.getProduto().getId());
    }

    public static ETPEditModal toEditModalEntity(ETP req) {
        return new ETPEditModal(
                req.getId(),
                req.getCodigo(),
                req.getProduto().getNome(),
                req.getProduto().getModelo().getNome(),
                req.getTamanho().getNumero(),
                req.getProduto().getCor().getNome(),
                req.getProduto().getValorCusto(),
                req.getProduto().getValorRevenda(),
                req.getLoja().getId(),
                req.getProduto().getId(),
                req.getItemPromocional(),
                req.getQuantidade());
    }

    public static  List<ETPTableRes> toTableResponse(List<ETP> req) {
        return req.stream().map(ETPMapper::toTableResponseEntity).toList();
    }

    public static List<ETPTableRes> toTableResponse(ListaGenerica<ETP> req) {
        List<ETPTableRes> res = new ArrayList<>();
        for (int i = 0; i < req.getTamanho(); i++) {
            res.add(toTableResponseEntity(req.getElemento(i)));
        }
        return res;
    }

    public static ETP reqToEntity(String codigo, Tamanho tamanho, Produto produto, Loja loja, ItemPromocional itemPromocional){
        ETP entidade = new ETP();
        entidade.setCodigo(codigo);
        entidade.setProduto(produto);
        entidade.setTamanho(tamanho);
        entidade.setLoja(loja);
        entidade.setQuantidade(0);
        entidade.setItemPromocional(itemPromocional);
        return entidade;
    }

    public static ETPTableRes entityToRes(ETP etp) {
        Integer id = etp.getId();
        String codigo = etp.getCodigo();
        String nome = etp.getProduto().getNome();
        String modelo = etp.getProduto().getModelo().getNome();
        Integer tamanho = etp.getTamanho().getNumero();
        String cor = etp.getProduto().getCor().getNome();
        Double preco = etp.getProduto().getValorRevenda();
        String loja = etp.getLoja().getNome();
        Integer quantidade = etp.getQuantidade();
        ItemPromocional itemPromocional = etp.getItemPromocional();
        Integer idProduto = etp.getProduto().getId();
        ETPTableRes respostaDto = new ETPTableRes(id, codigo, nome, modelo, tamanho, cor, preco, loja, itemPromocional, quantidade, idProduto);
        return respostaDto;
    }
}