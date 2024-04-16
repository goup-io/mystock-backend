package com.goup.utils;

import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.CargoRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.opencsv.CSVWriter;
import com.goup.entities.Estoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class CsvEstoque {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public void writeStockToCSV(List<Estoque> estoques) {
        String csvFile = "estoque.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            String[] header = {"ID Estoque", "ID Produto", "Nome Produto", "ID Loja", "Nome Loja", "ID Tamanho", "Quantidade"};
            writer.writeNext(header);

            for (Estoque estoque : estoques) {
                String[] data = {
                        String.valueOf(estoque.getId()),
                        String.valueOf(estoque.getProduto().getId()),
                        estoque.getProduto().getModelo().getNome(),
                        String.valueOf(estoque.getLoja().getId()),
                        estoque.getLoja().getNome(),
                        String.valueOf(estoque.getTamanho().getId()),
                        String.valueOf(estoque.getQuantidade())
                };
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}