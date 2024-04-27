package com.goup.controllers.lojas;

import com.goup.dtos.loja.LojaMapper;
import com.goup.dtos.loja.LojaResponseDto;
import com.goup.entities.lojas.Loja;
import com.goup.repositories.lojas.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lojas")
public class lojasController {

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping
    public ResponseEntity<List<LojaResponseDto>> listar(){
        List<Loja> lojas = lojaRepository.findAll();
        if (lojas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(LojaMapper.toResponse(lojas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LojaResponseDto> buscarPorId(@PathVariable Integer id){
        Optional<Loja> loja = lojaRepository.findById(id);
        if (loja.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(LojaMapper.toResponse(loja.get()));
    }
}
