package com.goup;

import com.goup.entities.Todo;
import com.goup.repositories.sqlserver.todo.TodoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class MyStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyStockApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> basicsApplicationListener(TodoRepository repository) {
		return event->repository.saveAll(Stream.of("A", "B", "C").map(name->new Todo("configuration", "congratulations, you have set up correctly!", true)).collect(Collectors.toList()))
				.forEach(System.out::println);
	}

}
