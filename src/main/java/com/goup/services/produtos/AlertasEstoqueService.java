package com.goup.services.produtos;

import com.goup.dtos.estoque.Notificacao;
import com.goup.entities.estoque.AlertasEstoque;
import com.goup.entities.historicos.Transferencia;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.historicos.TransferenciaRepository;
import com.goup.repositories.produtos.AlertasEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertasEstoqueService {
    @Autowired
    AlertasEstoqueRepository repository;
    @Autowired
    TransferenciaRepository transferenciaRepository;


    public List<AlertasEstoque> listar() {
        return repository.findAll();
    }

    public List<AlertasEstoque> listarPorLoja(int id_loja){
        return repository.findAllByEtpLoja_Id(id_loja);
    }

    public AlertasEstoque buscarPorId(Integer id) {
        Optional<AlertasEstoque> alerta = repository.findById(id);
        if(alerta.isEmpty()){
            throw new RegistroNaoEncontradoException("Alerta não encontrado");
        }
        return alerta.get();
    }

    public List<AlertasEstoque> listarPorFiltro(
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            @Param("id_loja") Integer id_loja
    ) {
        return repository.findAllByFiltro(dataInicio, dataFim, id_loja);
    }

    public Notificacao listarUltimoAviso(
            @Param("id_loja") Integer id_loja) {
        Optional<AlertasEstoque> alerta = repository.findLastAlertByLoja(id_loja);
        Optional<Transferencia> transf = transferenciaRepository.findLastTransferenciaByLoja(id_loja);
        if(alerta.isPresent() && transf.isPresent()){
            LocalDateTime alertaDt = alerta.get().getDataHora();
            LocalDateTime transfDt = transf.get().getDataHora();
            if(alertaDt.isAfter(transfDt)){
                return new Notificacao(alertaDt,
                        alerta.get().getDescricao());
            }
            Transferencia t = transf.get();
            String nomeProduto = t.getEtp().getProduto().getNome();
            Integer tamanho = t.getEtp().getTamanho().getNumero();
            String lojaColetora = t.getColetor().getLoja().getNome();
            return new Notificacao(
                    transfDt,
                    String.format("Transferência de produto %s de tamanho %d, solicitado pela %s ", nomeProduto, tamanho, lojaColetora));
        }else if(alerta.isPresent()){
            LocalDateTime alertaDt = alerta.get().getDataHora();
            return new Notificacao(alertaDt,
                    alerta.get().getDescricao());
        }else if(transf.isPresent()){
            LocalDateTime transfDt = transf.get().getDataHora();
            Transferencia t = transf.get();
            String nomeProduto = t.getEtp().getProduto().getNome();
            Integer tamanho = t.getEtp().getTamanho().getNumero();
            String lojaColetora = t.getColetor().getLoja().getNome();
            return new Notificacao(
                    transfDt,
                    String.format("Transferência de produto %s de tamanho %d, solicitado pela %s ", nomeProduto, tamanho, lojaColetora));
        }
        throw new RegistroNaoEncontradoException("Alerta ou Transferencia não encontrado");


    }
}
