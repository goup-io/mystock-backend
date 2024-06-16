package com.goup.services.produtos;

import com.goup.dtos.estoque.produtos.cor.CorMapper;
import com.goup.dtos.estoque.produtos.cor.CorReq;
import com.goup.entities.estoque.produtos.Cor;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.CorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class CorService {
    @Autowired
    private CorRepository repository;

    public Cor cadastrar(@RequestBody @Valid CorReq cor) {
        if(repository.existsByNome(cor.nome())){
            throw new RegistroConflitanteException("Cor já existente");
        }
        return this.repository.save(CorMapper.reqToEntity(cor));
    }

    public List<Cor> listar() {
        return repository.findAll();
    }

    public Cor buscarPorId(Integer id) {
        Optional<Cor> corOpt = repository.findById(id);
        if(corOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Cor não encontrada");
        }
        return corOpt.get();
    }

    public Cor atualizar(int id,CorReq corAtualizada) {
        Optional<Cor> corOpt = repository.findById(id);
        if(corOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Cor não encontrada");
        }
        corOpt.get().setNome(corAtualizada.nome());
        return this.repository.save(corOpt.get());
    }

    public void remover(int id) {
        if (!repository.existsById(id)) {
            throw new RegistroNaoEncontradoException("Cor não encontrada");
        }
        this.repository.deleteById(id);
    }
}
