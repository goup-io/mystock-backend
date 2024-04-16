package com.goup.controllers.usuario;

import com.goup.dtos.cargo.CargoAtualizarDescricaoDto;
import com.goup.dtos.cargo.CargoCadastrarDto;
import com.goup.dtos.cargo.CargoMapper;
import com.goup.dtos.cargo.CargoResponseDto;
import com.goup.entities.cargos.Cargo;
import com.goup.repositories.usuarios.CargoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping
    public ResponseEntity<List<CargoResponseDto>> listar(){
        List<Cargo> cargos = cargoRepository.findAll();

        if (cargos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(CargoMapper.toDto(cargos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoResponseDto> buscarPorId(@PathVariable Integer id){
        Optional<Cargo> cargo = cargoRepository.findById(id);

        if (cargo.isPresent()){
            return ResponseEntity.status(200).body(CargoMapper.responseToEntity(cargo.get()));
        }

        return ResponseEntity.status(404).build();
    }

    @PostMapping
    public ResponseEntity<Cargo> cadastrar(@RequestBody @Valid CargoCadastrarDto cadastrarDto){
        Cargo cargo = cargoRepository.save(CargoMapper.toEntity(cadastrarDto));
        return ResponseEntity.status(201).body(cargo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoResponseDto> atualizar(@RequestBody @Valid  CargoCadastrarDto cargoCadastrarDto, @PathVariable Integer id){
        Optional<Cargo> cargo = cargoRepository.findById(id);
        if (cargo.isPresent()){
            Cargo cargoEncontrado = cargo.get();
            cargoEncontrado.setNome(cargoCadastrarDto.nome());
            cargoEncontrado.setDescricao(cargoCadastrarDto.descricao());
            cargoRepository.save(cargoEncontrado);
            return ResponseEntity.status(201).body(CargoMapper.responseToEntity(cargoEncontrado));
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/descricao/{id}")
    public ResponseEntity<CargoResponseDto> atualizarDescricao(@RequestBody @Valid CargoAtualizarDescricaoDto cargoAtualizarDescricaoDto, @PathVariable Integer id){
        Optional<Cargo> cargo = cargoRepository.findById(id);
        if (cargo.isPresent()){
            Cargo cargoEncontrado = cargo.get();
            cargoEncontrado.setDescricao(cargoAtualizarDescricaoDto.descricao());
            cargoRepository.save(cargoEncontrado);
            return ResponseEntity.status(201).body(CargoMapper.responseToEntity(cargoEncontrado));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        Optional<Cargo> cargo = cargoRepository.findById(id);
        if (cargo.isPresent()){
            Cargo cargoEncontrado = cargo.get();
            cargoRepository.delete(cargoEncontrado);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
