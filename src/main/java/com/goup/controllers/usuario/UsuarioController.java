package com.goup.controllers.usuario;

import com.goup.dtos.usuario.*;
import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.services.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(@RequestBody @Valid UsuarioCadastrarDto novoUsuario) {
        UsuarioResponseDto userCadastrar = usuarioService.criarUsuario(novoUsuario);
        return ResponseEntity.status(201).body(userCadastrar);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResTableDto>> listar() {
        return ResponseEntity.status(200).body(usuarioService.buscarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarUsuarioPorId(@PathVariable("id") int id) {
        return ResponseEntity.status(200).body(usuarioService.buscarUsuarioPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@RequestBody @Valid UsuarioAtualizarDto novoUsuario, @PathVariable Integer id) {
        UsuarioResponseDto userAtualizar = usuarioService.atualizarUsuario(novoUsuario, id);
        return ResponseEntity.status(200).body(userAtualizar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
       usuarioService.deletarUsuario(id);
         return ResponseEntity.status(204).build();
    }
}