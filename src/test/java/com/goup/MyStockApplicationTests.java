package com.goup;

import com.goup.controllers.csv.CsvController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.goup.entities.usuarios.Usuario;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MyStockApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testWriteUsersToCSV() {
		// Cria uma lista de usuários
		Usuario user1 = new Usuario();
		user1.setId(1); // Set the id
		// Configure o usuário 1
		// ...

		Usuario user2 = new Usuario();
		user2.setId(2); // Set the id
		// Configure o usuário 2
		// ...

		List<Usuario> users = Arrays.asList(user1, user2);

		// Cria um CsvClient
		CsvController csvController = new CsvController();

		// Escreve os usuários no arquivo CSV
		try {
			byte[] csv = csvController.writeUsersToCSV(users);
			FileWriter fileWriter = new FileWriter("users.csv");
			fileWriter.write(new String(csv));
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Verifica se o arquivo CSV foi criado
		File csvFile = new File("users.csv");
		assertTrue(csvFile.exists());
	}
	}
