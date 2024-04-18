package com.goup.controllers.usuario;

import com.goup.dtos.usuario.UsuarioBuiltDto;
import com.goup.dtos.usuario.UsuarioCadastrarDto;
import com.goup.dtos.usuario.UsuarioMapper;
import com.goup.dtos.usuario.UsuarioResponseDto;
import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.CargoRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(@RequestBody @Valid UsuarioCadastrarDto novoUsuario) {
        Optional<Cargo> cargoSearch = cargoRepository.findById(novoUsuario.idCargo());
        Cargo cargo;
        if(cargoSearch.isPresent()){
            cargo = cargoSearch.get();
        } else {
            return ResponseEntity.status(404).build();
        }

        Optional<Loja> lojaSearch = lojaRepository.findById(novoUsuario.idLoja());
        Loja loja;
        if(lojaSearch.isPresent()){
            loja = lojaSearch.get();
        } else {
            return ResponseEntity.status(404).build();
        }


        UsuarioBuiltDto usuarioBuiltDto = new UsuarioBuiltDto(
                novoUsuario.nome(),
                cargo,
                novoUsuario.email(),
                novoUsuario.telefone(),
                loja
        );

        Usuario userCadastrar = UsuarioMapper.toEntity(usuarioBuiltDto);
        usuarioRepository.save(userCadastrar);

        return ResponseEntity.status(201).body(UsuarioMapper.entityToReponse(userCadastrar));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuariosEncontrados = usuarioRepository.findAllWithJoin();
        if (usuariosEncontrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuariosEncontrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        return usuarioOpt.map(usuario -> ResponseEntity.status(200).body(usuario)).orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Usuario usuario, @PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            usuarioRepository.save(usuarioOpt.get());
            return ResponseEntity.status(200).body(usuario);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(204).body(usuarioOpt.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}