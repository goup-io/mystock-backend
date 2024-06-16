package com.goup.services.produtos;

import com.goup.dtos.estoque.produtos.modelos.tipo.TipoMapper;
import com.goup.dtos.estoque.produtos.modelos.tipo.TipoReq;
import com.goup.entities.estoque.produtos.modelos.Tipo;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.TipoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class TipoService {
    @Autowired
    private TipoRepository repository;

    @PostMapping
    public Tipo cadastrar(@RequestBody @Valid TipoReq tipo) {
        return this.repository.save(TipoMapper.reqToEntity(tipo));
    }

    @GetMapping
    public List<Tipo> listar() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Tipo buscarPorId(@PathVariable Integer id) {
        Optional<Tipo> tipoOpt = repository.findById(id);
        if(tipoOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Tipo não encontrado");
        }
        return tipoOpt.get();
    }

    @PutMapping("/{id}")
    public Tipo atualizar(@PathVariable int id, @RequestBody @Valid TipoReq tipoAtualizado) {
        Optional<Tipo> tipoOpt = repository.findById(id);
        if (tipoOpt.isEmpty()) {
            throw new RegistroNaoEncontradoException("Tipo não encontrado");
        }
        tipoOpt.get().setNome(tipoAtualizado.nome());
        return this.repository.save(tipoOpt.get());
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id) {
        if (!repository.existsById(id)) {
            throw new RegistroNaoEncontradoException("Tipo não encontrado");
        }
        this.repository.deleteById(id);
    }
}
