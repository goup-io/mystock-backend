package com.goup.controllers.usuario;

import com.goup.dtos.usuario.*;
import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.usuarios.login.Login;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.CargoRepository;
import com.goup.repositories.usuarios.LoginRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(@RequestBody @Valid UsuarioCadastrarDto novoUsuario) {

        Cargo cargo = buscarCargoPorId(novoUsuario.idCargo());
        if(cargo == null){
            return ResponseEntity.status(404).build();
        }

        Loja loja = buscarLojaPorId(novoUsuario.idLoja());
        if(loja == null){
            return ResponseEntity.status(404).build();
        }

        UsuarioBuiltDto usuarioBuiltDto = new UsuarioBuiltDto(
                novoUsuario.nome(),
                cargo,
                novoUsuario.email(),
                novoUsuario.telefone(),
                loja
        );

        Usuario userCadastrar = usuarioRepository.save(UsuarioMapper.toEntity(usuarioBuiltDto));

        return ResponseEntity.status(201).body(UsuarioMapper.entityToReponse(userCadastrar));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResTableDto>> listar() {
        List<Usuario> usuariosEncontrados = usuarioRepository.findAllWithJoin();
        if (usuariosEncontrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<UsuarioResTableDto> listaDto = new ArrayList<>();
        String usuarioLogin;
        for (Usuario usuariosEncontrado : usuariosEncontrados) {
            Login loginById = loginRepository.findLoginById(usuariosEncontrado.getId());
            if (loginById == null){
                usuarioLogin = "---";
            } else {
                usuarioLogin = loginById.getUsername();
            }

            listaDto.add(new UsuarioResTableDto(
                    usuariosEncontrado.getId(),
                    usuariosEncontrado.getCodigoVenda(),
                    usuariosEncontrado.getNome(),
                    usuariosEncontrado.getEmail(),
                    usuariosEncontrado.getTelefone(),
                    usuariosEncontrado.getLoja().getNome(),
                    usuariosEncontrado.getCargo().getNome(),
                    usuarioLogin
            ));
        }

        return ResponseEntity.status(200).body(listaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarUsuarioPorId(@PathVariable("id") int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        return usuarioOpt.map(usuario -> ResponseEntity.status(200).body(UsuarioMapper.entityToReponse(usuario))).orElseGet(() -> ResponseEntity.status(404).build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@RequestBody @Valid UsuarioAtualizarDto novoUsuario, @PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {

            Cargo cargo = buscarCargoPorId(novoUsuario.idCargo());
            if(cargo == null){
                return ResponseEntity.status(404).build();
            }

            Loja loja = buscarLojaPorId(novoUsuario.idLoja());
            if(loja == null){
                return ResponseEntity.status(404).build();
            }

            UsuarioBuiltDto usuarioBuiltDto = new UsuarioBuiltDto(
                    novoUsuario.nome(),
                    usuarioOpt.get().getCargo(),
                    novoUsuario.email(),
                    novoUsuario.telefone(),
                    usuarioOpt.get().getLoja()
            );

            Usuario usuario = UsuarioMapper.toEntity(usuarioBuiltDto);

            return ResponseEntity.status(200).body(UsuarioMapper.entityToReponse(usuario));
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    public Cargo buscarCargoPorId(int idCargo) {
        Optional<Cargo> cargoSearch = cargoRepository.findById(idCargo);
        if(cargoSearch.isPresent()){
            return cargoSearch.get();
        } else {
            return null;
        }
    }

    public Loja buscarLojaPorId(int idLoja) {
        Optional<Loja> lojaSearch = lojaRepository.findById(idLoja);
        if(lojaSearch.isPresent()){
            return lojaSearch.get();
        } else {
            return null;
        }
    }
}