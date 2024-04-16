package com.goup.utils;

import com.goup.entities.usuarios.Usuario;
import com.goup.repositories.usuarios.LoginRepository;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvCliente {



    public void writeUsersToCSV(List<Usuario> users) {
        String csvFile = "users.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile), ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, "\n")) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}