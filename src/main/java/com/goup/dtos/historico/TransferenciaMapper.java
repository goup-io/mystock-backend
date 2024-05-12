package com.goup.dtos.historico;

import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.estoque.ETPTableRes;
import com.goup.dtos.estoque.produtos.ProdutoMapper;
import com.goup.dtos.estoque.produtos.ProdutoRes;
import com.goup.dtos.historico.TransferenciaReq;
import com.goup.entities.estoque.ETP;
import com.goup.entities.historicos.Transferencia;
import com.goup.entities.usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class TransferenciaMapper {
    public static Transferencia reqToEntity(TransferenciaReq transf, ETP etp, Usuario coletor){
        Transferencia entity = new Transferencia();
        entity.setDataHora(LocalDateTime.now());
        entity.setColetor(coletor);
        entity.setQuantidadeSolicitada(transf.quantidadeSolicitada());
        entity.setEtp(etp);
        return entity;
    }

    public static TransferenciaRes entityToRes(Transferencia transf){
        Integer id = transf.getId();
        LocalDateTime dataHora = transf.getDataHora();
        Boolean status = transf.getStatus();
        String loja_coletora = transf.getColetor().getLoja().getNome();
        String loja_liberadora = transf.getEtp().getLoja().getNome();
        ETPTableRes etp = ETPMapper.entityToRes(transf.getEtp());
        Integer tamanho = transf.getEtp().getTamanho().getNumero();
        Integer quantidadeSolicitada = transf.getQuantidadeSolicitada();
        Integer quantidadeLiberada = transf.getQuantidadeLiberada();
        String coletor = transf.getColetor().getNome();
        String liberador = transf.getLiberador() != null ? transf.getLiberador().getNome() : null;

        TransferenciaRes responseDto = new TransferenciaRes(id, dataHora, status, loja_coletora,
        loja_liberadora, etp, tamanho, quantidadeSolicitada, quantidadeLiberada, coletor, liberador);
        return responseDto;
    }

    public static List<TransferenciaRes> listToListReq(List<Transferencia> lista){
        return lista.stream().map(TransferenciaMapper::entityToRes).toList();
    }

    public static Transferencia aprovar(Transferencia transf, TransferenciaReqAprovar aprovacao, Usuario liberador){
        transf.setLiberador(liberador);
        transf.setQuantidadeLiberada(aprovacao.quantidadeLiberada());
        transf.setStatus(true);
        return transf;
    }

}
