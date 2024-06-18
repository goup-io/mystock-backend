package com.goup.services.produtos;

import com.goup.entities.estoque.AlertasEstoque;
import com.goup.exceptions.RegistroNaoEncontradoException;
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
}
