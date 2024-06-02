package com.goup.dtos.historico.transferencia;

import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.estoque.ETPTableRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.historicos.StatusTransferencia;
import com.goup.entities.historicos.Transferencia;
import com.goup.entities.usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class TransferenciaMapper {
    public static Transferencia reqToEntity(TransferenciaReq transf, ETP etp, Usuario coletor, StatusTransferencia pendente){
        Transferencia entity = new Transferencia();
        entity.setDataHora(LocalDateTime.now());
        entity.setColetor(coletor);
        entity.setQuantidadeSolicitada(transf.quantidadeSolicitada());
        entity.setEtp(etp);
        entity.setStatus(pendente);
        return entity;
    }

    public static TransferenciaRes entityToRes(Transferencia transf){
        Integer id = transf.getId();
        LocalDateTime dataHora = transf.getDataHora();
        StatusTransferencia status = transf.getStatus();
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

    public static Transferencia aprovar
    (
        Transferencia transf,
        TransferenciaReqAprovar aprovacao,
        Usuario liberador,
        StatusTransferencia aceito
    ){
        transf.setLiberador(liberador);
        transf.setQuantidadeLiberada(aprovacao.quantidadeLiberada());
        transf.setStatus(aceito);
        return transf;
    }

    public static Transferencia rejeitar
    (
        Transferencia transf,
        Usuario liberador,
        StatusTransferencia negado
    ){
        transf.setLiberador(liberador);
        transf.setStatus((negado));
        return transf;
    }

}
