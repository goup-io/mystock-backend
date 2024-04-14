package com.goup.controllers.usuario;

import com.goup.dtos.requests.UsuarioCadastrarDTO;
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
    private final UsuarioRepository repository;
    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioCadastrarDTO novoUsuario) {
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

        Usuario usuario = new Usuario(novoUsuario.codigoVenda(), novoUsuario.nome(), cargo, novoUsuario.email(), novoUsuario.telefone(), loja);
        repository.save(usuario);
        return ResponseEntity.status(201).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuariosEncontrados = repository.findAllWithJoin();
        if (usuariosEncontrados.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuariosEncontrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = repository.findById(id);

        return usuarioOpt.map(usuario -> ResponseEntity.status(200).body(usuario)).orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Usuario usuario, @PathVariable int id) {
        Optional<Usuario> usuarioOpt = repository.findById(id);

        if (usuarioOpt.isPresent()) {
            repository.save(usuarioOpt.get());
            return ResponseEntity.status(200).body(usuario);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = repository.findById(id);
        if (usuarioOpt.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(204).body(usuarioOpt.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

/*

---to remove---

    Utils utils = new Utils();

    @GetMapping("/vendedor/all")
    public ResponseEntity<Vendedor[]> buscarVendedores(){
        Vendedor[] vendedores = listUsers.stream()
                .filter(usuario -> usuario instanceof Vendedor)
                .toArray(Vendedor[]::new);

        if (vendedores.length < 1){
            return ResponseEntity.status(404).build();
        }

        utils.sortVendedorNome(vendedores);

        return ResponseEntity.status(200).body(vendedores);

    }
*/
}