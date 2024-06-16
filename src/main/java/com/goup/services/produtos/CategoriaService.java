package com.goup.services.produtos;

import com.goup.dtos.estoque.produtos.modelos.categoria.CategoriaMapper;
import com.goup.dtos.estoque.produtos.modelos.categoria.CategoriaReq;
import com.goup.entities.estoque.produtos.modelos.Categoria;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    public Categoria cadastrar(@RequestBody @Valid CategoriaReq categoria) {
        return this.repository.save(CategoriaMapper.reqToEntity(categoria));
    }

    @GetMapping
    public List<Categoria> listar() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Integer id) {
        Optional<Categoria> categoriaOpt = repository.findById(id);
        if(categoriaOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Categoria não encontrada");
        }
        return categoriaOpt.get();
    }

    @PutMapping("/{id}")
    public Categoria atualizar(@PathVariable int id, @RequestBody @Valid CategoriaReq categoriaAtualizada) {
        Optional<Categoria> categoriaOpt = repository.findById(id);
        if (categoriaOpt.isPresent()) {
            categoriaOpt.get().setNome(categoriaAtualizada.nome());
            this.repository.save(categoriaOpt.get());
            return categoriaOpt.get();
        }
        throw new RegistroNaoEncontradoException("Categoria não encontrada");
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable int id) {
        if (!repository.existsById(id)) {
            throw new RegistroNaoEncontradoException("Categoria não encontrada");
        }
        this.repository.deleteById(id);
    }
}
