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
import pagseguro.data.Customer;
import pagseguro.service.PixOrder;
import pagseguro.service.PixOrderService;
import pagseguro.service.QrCode;
import pagseguro.service.response.Link;
import pagseguro.service.response.ResponseError;

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

	public static final String CPF_CNPJ_CLIENTE = "14880686077";


	public static void main(String[] args) {
		SpringApplication.run(MyStockApplication.class, args);

		/**
		 * CPF válido gerado aleatoriamente em https://www.4devs.com.br
		 */

			final var env = Dotenv.load();
			final var baseUrl = env.get("PAYMENT_SERVICE_URL");
			final var token = env.get("PAYMENT_SERVICE_TOKEN");
			final var notificationUrl = env.get("PAYMENT_NOTIFICATION_URL");
			final var reference_id = env.get("PAYMENT_REFERENCE_ID");

			final var service = new PixOrderService(baseUrl, token);
			final var customer = new Customer("Manoel", "teste@teste.com", CPF_CNPJ_CLIENTE);
			final int valorCentavos = 100;
			final var qrcode = new QrCode(valorCentavos, OffsetDateTime.now().plusDays(1));
			final var request = new PixOrder(reference_id, customer, qrcode, notificationUrl);
			try {
				final PixOrder response = service.send(request);
				printResponse(response);
			} catch (final ResponseError e) {
				System.err.println("Erro ao processar requisição");
				e.getError_messages().forEach(System.err::println);
			}
		}

		private static void printResponse(final PixOrder response) {
			System.out.printf("Order reference_id: %s%n", response.reference_id());
			System.out.printf("QRCode id: %s%n", response.id());
			System.out.printf("created_at: %s%n", response.created_at());
			System.out.printf("customer: %s%n", response.customer());
			printLinks(response.links(), "");
			response.notification_urls().forEach(System.out::println);
			response.qr_codes().forEach(MyStockApplication::printQrCode);
		}

		private static void printQrCode(final QrCode code) {
			System.out.println("QRCode:");
			System.out.printf("\tid: %s%n", code.id());
			System.out.printf("\ttext: %s%n", code.text());
			System.out.printf("\texpiration_date: %s%n", code.expiration_date());
			System.out.printf("\tarrangements: %s%n", code.arrangements());
			printLinks(code.links(), "\t");
		}

		private static void printLinks(final List<Link> links, final String ident) {
			System.out.printf("%slinks:%n", ident);
			links.forEach(link -> System.out.printf("%s\t%s%n", ident, link));
		}
	}



