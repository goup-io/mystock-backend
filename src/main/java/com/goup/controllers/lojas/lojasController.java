package com.goup.controllers.lojas;

import com.goup.dtos.loja.LojaMapper;
import com.goup.dtos.loja.LojaReq;
import com.goup.dtos.loja.LojaRes;
import com.goup.entities.lojas.Loja;
import com.goup.repositories.lojas.LojaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${mystock.api.prefix}/lojas")
public class lojasController {
    @Autowired
    private LojaRepository repository;

    @PostMapping
    public ResponseEntity<LojaRes> cadastrar(@RequestBody @Valid LojaReq loja) {
        final Loja lojaSalva = this.repository.save(LojaMapper.reqToEntity(loja));
        return ResponseEntity.status(201).body(LojaMapper.toDto(lojaSalva));
    }

    @GetMapping
    public ResponseEntity<List<LojaRes>> listar() {
        List<Loja> lojas = this.repository.findAll();

        if (lojas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(LojaMapper.toDto(lojas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LojaRes> buscarPorId(@PathVariable Integer id) {
        Optional<Loja> lojaOpt = repository.findById(id);
        if (lojaOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(LojaMapper.toDto(lojaOpt.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LojaRes> atualizar(@PathVariable int id, @RequestBody @Valid LojaReq lojaAtualizada) {
        Optional<Loja> lojaOpt = repository.findById(id);
        if (lojaOpt.isPresent()) {
            Loja atualizado = LojaMapper.updateEntity(lojaOpt.get(), lojaAtualizada);
            this.repository.save(atualizado);
            return ResponseEntity.status(200).body(LojaMapper.toDto(atualizado));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        if (repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
