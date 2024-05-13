package com.goup.controllers.csv;


import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.estoque.ETPTableRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.ETPRepository;
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
import java.nio.charset.StandardCharsets;
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

    @Autowired
    private ETPRepository etpRepository;





    public byte[] writeUsersToCSV(List<Usuario> users) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(0xef);
        stream.write(0xbb);
        stream.write(0xbf);
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, "\n");

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

    public byte[] writeETPToCSV(List<ETPTableRes> etps) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(0xef);
        stream.write(0xbb);
        stream.write(0xbf);
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, "\n");

        // Header
        String[] header = { "Código", "Nome", "Modelo", "Tamanho", "Cor", "Preço", "Loja", "Quantidade"};
        writer.writeNext(header);

        // Write the data of the entities
        for (ETPTableRes etp : etps) {
            String[] data = {
                    etp.codigo(),
                    etp.nome(),
                    etp.modelo(),
                    String.valueOf(etp.tamanho()),
                    etp.cor(),
                    String.valueOf(etp.preco()),
                    etp.loja(),
                    String.valueOf(etp.quantidade())
            };
            writer.writeNext(data);
        }

        writer.close();
        return stream.toByteArray();
    }


    @GetMapping("funcionarios-todas-as-loja")
    public ResponseEntity<ByteArrayResource> gerarCsvParaTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (!usuarios.isEmpty()) {
            try {
                byte[] csvData = this.writeUsersToCSV(usuarios);
                ByteArrayResource resource = new ByteArrayResource(csvData);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=funcionarios-todas-as-lojas.csv")
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

    @GetMapping("funcionarios-por-loja/{lojaId}")
    public ResponseEntity<ByteArrayResource> gerarCsvPorLoja(@PathVariable int lojaId) {
        Optional<Loja> lojaOpt = lojaRepository.findById(lojaId);
        if (lojaOpt.isPresent()) {
            List<Usuario> usuarios = usuarioRepository.findAllByLoja(lojaOpt.get());
            if (!usuarios.isEmpty()) {
                try {
                    byte[] csvData = this.writeUsersToCSV(usuarios);
                    ByteArrayResource resource = new ByteArrayResource(csvData);

                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=funcionarios-loja"+lojaOpt.get().getNome()+".csv")
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



    @GetMapping("/estoque-geral")
    public ResponseEntity<ByteArrayResource> gerarCsvEstoqueGeral() {

        List<ETPTableRes> etps = ETPMapper.toTableResponse(etpRepository.findAll());

        if (!etps.isEmpty()) {
            try {
                byte[] csvData = this.writeETPToCSV(etps);
                ByteArrayResource resource = new ByteArrayResource(csvData);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=estoque-geral.csv")
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


    @GetMapping("/etp/estoque-por-loja/{lojaId}")
    public ResponseEntity<ByteArrayResource> gerarCsvEstoquePorLoja(@PathVariable int lojaId) {
        Optional<Loja> lojaOpt = lojaRepository.findById(lojaId);
        if (lojaOpt.isPresent()) {
            List<ETP> etps = etpRepository.findAllByLoja(lojaOpt.get());
            List<ETPTableRes> etpTableResList = ETPMapper.toTableResponse(etps);

            if (!etpTableResList.isEmpty()) {
                try {
                    byte[] csvData = this.writeETPToCSV(etpTableResList);
                    ByteArrayResource resource = new ByteArrayResource(csvData);

                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=estoque-loja"+lojaOpt.get().getNome()+".csv")
                            .contentType(MediaType.parseMediaType("application/csv"))
                            .body(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(500).build(); // Internal Server Error
                }
            } else {
                return ResponseEntity.status(404).build();
            }
        } else {
            return ResponseEntity.status(404).build();
        }
    }




}



