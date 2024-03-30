package com.goup.controllers;

import com.goup.entities.usuarios.Gerente;
import com.goup.entities.usuarios.GerenteGeral;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.usuarios.Vendedor;
import com.goup.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final List<Usuario> listUsers = new ArrayList<>();
    private int id;
    private int codigoVenda = 100;

    @PostMapping("/gerenteGeral")
    public ResponseEntity<GerenteGeral> cadastrarGerenteGeral(@RequestBody @Valid GerenteGeral gerenteGeralCadastrado) {

        gerenteGeralCadastrado.setId(++id);
        gerenteGeralCadastrado.setCodigoVenda(++codigoVenda);
        listUsers.add(gerenteGeralCadastrado);
        return ResponseEntity.status(201).body(gerenteGeralCadastrado);

    }

    @PostMapping("/gerente")
    public ResponseEntity<Gerente> cadastrarGerente(@RequestBody @Valid Gerente gerenteCadastrado) {
        String s1 = gerenteCadastrado.getCargo().substring(0, 1).toUpperCase();
        String cargoFormatado = s1 + gerenteCadastrado.getCargo().substring(1);
        gerenteCadastrado.setCargo(cargoFormatado);
        gerenteCadastrado.setId(++id);
        gerenteCadastrado.setCodigoVenda(++codigoVenda);
        listUsers.add(gerenteCadastrado);
        return ResponseEntity.status(201).body(gerenteCadastrado);

    }

    @PostMapping("/vendedor")
    public ResponseEntity<Vendedor> cadastrarVendedor(@RequestBody @Valid Vendedor vendedorCadastrado) {

        vendedorCadastrado.setId(++id);
        vendedorCadastrado.setCodigoVenda(++codigoVenda);
        listUsers.add(vendedorCadastrado);
        return ResponseEntity.status(201).body(vendedorCadastrado);

    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        if (listUsers.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(listUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int id) {
        Usuario usuarioProcurado = buscarPorId(id);

        if (usuarioProcurado == null) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(usuarioProcurado);
    }

    @PutMapping("/gerenteGeral/{id}")
    public ResponseEntity<GerenteGeral> atualizar(@RequestBody @Valid GerenteGeral gerenteGeral, @PathVariable int id) {

        // Busca o usuário pelo id e atualiza os dados
        GerenteGeral gerenteGeralAtualizado = (GerenteGeral) buscarPorId(id);

        if (gerenteGeralAtualizado == null) {
            return ResponseEntity.status(404).build();
        }

        gerenteGeralAtualizado.setNome(gerenteGeral.getNome());
        gerenteGeralAtualizado.setCargo(gerenteGeral.getCargo());
        gerenteGeralAtualizado.setTelefone(gerenteGeral.getTelefone());
        gerenteGeralAtualizado.setEmail(gerenteGeral.getEmail());
        gerenteGeralAtualizado.setEmail(gerenteGeral.getSenha());

        return ResponseEntity.status(200).body(gerenteGeralAtualizado);
    }

    @PutMapping("/gerente/{id}")
    public ResponseEntity<Gerente> atualizar(@RequestBody @Valid Gerente gerente, @PathVariable int id) {



        // Busca o Gerente pelo id e atualiza os dados
        Gerente gerenteAtualizado = (Gerente) buscarPorId(id);

        if (gerenteAtualizado == null) {
            return ResponseEntity.status(404).build();
        }

        gerenteAtualizado.setNome(gerente.getNome());
        gerenteAtualizado.setCargo(gerente.getCargo());
        gerenteAtualizado.setTelefone(gerente.getTelefone());
        gerenteAtualizado.setEmail(gerente.getEmail());
        gerenteAtualizado.setEmail(gerente.getSenha());

        return ResponseEntity.status(200).body(gerenteAtualizado);
    }

    @PutMapping("/vendedor/{id}")
    public ResponseEntity<Vendedor> atualizar(@RequestBody @Valid Vendedor vendedor, @PathVariable int id) {

        // Busca o Vendedor pelo id e atualiza os dados
        Vendedor vendedorAtualizado = (Vendedor) buscarPorId(id);

        if (vendedorAtualizado == null) {
            return ResponseEntity.status(404).build();
        }

        vendedorAtualizado.setNome(vendedor.getNome());
        vendedorAtualizado.setCargo(vendedor.getCargo());
        vendedorAtualizado.setTelefone(vendedor.getTelefone());

        return ResponseEntity.status(200).body(vendedorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable int id) {

        Usuario user = buscarPorId(id);

        if (user == null) {
            return ResponseEntity.status(404).build();
        }

        listUsers.remove(user);
        return ResponseEntity.status(204).body(user);
    }

    private Usuario buscarPorId(int id){
        for (Usuario user : listUsers) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

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

}