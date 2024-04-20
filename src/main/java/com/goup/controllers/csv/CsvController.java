package com.goup.controllers.csv;


import com.goup.dtos.usuario.UsuarioBuiltDto;
import com.goup.dtos.usuario.UsuarioCadastrarDto;
import com.goup.dtos.usuario.UsuarioMapper;
import com.goup.dtos.usuario.UsuarioResponseDto;
import com.goup.entities.Estoque;
import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.CargoRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.repositories.wip.EstoqueRepository;
import com.goup.utils.CsvCliente;
import com.goup.utils.CsvEstoque;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private LojaRepository lojaRepository;


    @GetMapping("/csv")
    public ResponseEntity<Void> gerarCsvParaTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (!usuarios.isEmpty()) {
            CsvCliente csvCliente = new CsvCliente();
            csvCliente.writeUsersToCSV(usuarios);

            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/csv/loja/{lojaId}")
    public ResponseEntity<Void> gerarCsvPorLoja(@PathVariable int lojaId) {
        Optional<Loja> lojaOpt = lojaRepository.findById(lojaId);
        if (lojaOpt.isPresent()) {
            List<Usuario> usuarios = usuarioRepository.findAllByLoja(lojaOpt.get());
            if (!usuarios.isEmpty()) {
                CsvCliente csvCliente = new CsvCliente();
                csvCliente.writeUsersToCSV(usuarios);
                return ResponseEntity.status(200).build();
            } else {
                return ResponseEntity.status(204).build();
            }
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/csv/produtos/loja/{lojaId}")
    public ResponseEntity<Void> gerarCsvProdutosPorLoja(@PathVariable int lojaId) {
        List<Estoque> estoques = estoqueRepository.findAllByLojaId(lojaId);
        if (!estoques.isEmpty()) {
            CsvEstoque csvEstoque = new CsvEstoque();
            csvEstoque.writeStockToCSV(estoques);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @GetMapping("/csv/produtos")
    public ResponseEntity<Void> gerarCsvTodosProdutos() {
        List<Estoque> estoques = estoqueRepository.findAll();
        if (!estoques.isEmpty()) {
            CsvEstoque csvProdutos = new CsvEstoque();
            csvProdutos.writeStockToCSV(estoques);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}



