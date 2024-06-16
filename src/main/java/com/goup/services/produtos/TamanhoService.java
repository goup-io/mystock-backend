package com.goup.services.produtos;

import com.goup.dtos.estoque.tamanho.TamanhoMapper;
import com.goup.dtos.estoque.tamanho.TamanhoReq;
import com.goup.entities.estoque.Tamanho;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.TamanhoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class TamanhoService {
    @Autowired
    private TamanhoRepository repository;

    public Tamanho cadastrar(@RequestBody @Valid TamanhoReq tamanho) {
        if(repository.existsByNumero(tamanho.numero())){
            throw new RegistroConflitanteException("Tamanho já existente!");
        }
        return this.repository.save(TamanhoMapper.reqToEntity(tamanho));
    }

    public List<Tamanho> listar() {
       return this.repository.findAll();
    }

    public Tamanho buscarPorNumeracao(@PathVariable Integer numero) {
        Optional<Tamanho> tamanhoOpt = repository.findByNumero(numero);
        if(tamanhoOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Tamanho não encontrado");
        }
        return tamanhoOpt.get();
    }

    public void deletar(int numero) {
        if (!repository.existsByNumero(numero)) {
            throw new RegistroNaoEncontradoException("Tamanho não encontrado");
        }
        this.repository.deleteByNumero(numero);
    }
}
