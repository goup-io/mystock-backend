package com.goup;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.OffsetDateTime;
import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "MyStockAPI", version = "v1"),
		security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
		name = "bearerAuth",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT"
)


//@SecurityScheme(name = "bearerAuth", scheme = "Bearer", type= SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class MyStockApplication {



	public static void main(String[] args) {
		SpringApplication.run(MyStockApplication.class, args);
	}
	}



