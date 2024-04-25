package com.goup.controllers.csv;


import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.CargoRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/csv")
public class CsvController {



/*    @Autowired
    private EstoqueRepository estoqueRepository;*/

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private LojaRepository lojaRepository;


    public byte[] writeUsersToCSV(List<Usuario> users) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, "\n");

        // Header
        String[] header = { "ID", "Nome", "Telefone", "Cargo", "Loja" };
        writer.writeNext(header);

        // Escreve os dados dos usuários
        for (Usuario user : users) {
            String cargoNome = null;

            String[] data = {
                    String.valueOf(user.getId()),
                    user.getNome(),
                    user.getTelefone(),
                    user.getCargo().getNome(),
                    user.getLoja() != null ? user.getLoja().getNome() : null
            };
            writer.writeNext(data);
        }

        writer.close();
        return stream.toByteArray();
    }

    @GetMapping()
    public ResponseEntity<ByteArrayResource> gerarCsvParaTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (!usuarios.isEmpty()) {
            try {
                byte[] csvData = this.writeUsersToCSV(usuarios);
                ByteArrayResource resource = new ByteArrayResource(csvData);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=users.csv")
                        .contentType(MediaType.parseMediaType("application/csv"))
                        .body(resource);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).build(); // Internal Server Error
            }
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("loja/{lojaId}")
    public ResponseEntity<ByteArrayResource> gerarCsvPorLoja(@PathVariable int lojaId) {
        Optional<Loja> lojaOpt = lojaRepository.findById(lojaId);
        if (lojaOpt.isPresent()) {
            List<Usuario> usuarios = usuarioRepository.findAllByLoja(lojaOpt.get());
            if (!usuarios.isEmpty()) {
                try {
                    byte[] csvData = this.writeUsersToCSV(usuarios);
                    ByteArrayResource resource = new ByteArrayResource(csvData);

                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=users.csv")
                            .contentType(MediaType.parseMediaType("application/csv"))
                            .body(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(500).build(); // Internal Server Error
                }
            } else {
                return ResponseEntity.status(204).build();
            }
        } else {
            return ResponseEntity.status(404).build();
        }
    }
/*
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
    }*/
}



