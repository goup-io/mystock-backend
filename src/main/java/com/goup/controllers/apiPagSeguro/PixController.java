package com.goup.controllers.apiPagSeguro;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pagseguro.data.Customer;
import pagseguro.service.PixOrder;
import pagseguro.service.PixOrderService;
import pagseguro.service.QrCode;
import pagseguro.service.response.Link;
import pagseguro.service.response.ResponseError;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/PIX")
public class PixController {

    @GetMapping("/createPixOrder")
    public ResponseEntity<String> createPixOrder() {
        String CPF_CNPJ_CLIENTE = "14880686077";

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
            return ResponseEntity.ok(printResponse(response));
        } catch (final ResponseError e) {
            return ResponseEntity.status(500).body("Erro ao processar requisição: " + e.getError_messages());
        }
    }

    private static String printResponse(final PixOrder response) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Order reference_id: %s%n", response.reference_id()));
        sb.append(String.format("QRCode id: %s%n", response.id()));
        sb.append(String.format("created_at: %s%n", response.created_at()));
        sb.append(String.format("customer: %s%n", response.customer()));
        sb.append(printLinks(response.links(), ""));
        response.notification_urls().forEach(url -> sb.append(url).append("\n"));
        response.qr_codes().forEach(code -> sb.append(printQrCode(code)).append("\n"));
        return sb.toString();
    }

    private static String printQrCode(final QrCode code) {
        StringBuilder sb = new StringBuilder();
        sb.append("QRCode:\n");
        sb.append(String.format("\tid: %s%n", code.id()));
        sb.append(String.format("\ttext: %s%n", code.text()));
        sb.append(String.format("\texpiration_date: %s%n", code.expiration_date()));
        sb.append(String.format("\tarrangements: %s%n", code.arrangements()));
        sb.append(printLinks(code.links(), "\t"));
        return sb.toString();
    }

    private static String printLinks(final List<Link> links, final String ident) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%slinks:%n", ident));
        links.forEach(link -> sb.append(String.format("%s\tLink[rel=%s, href=%s, media=%s, type=%s]%n", ident, link.rel(), link.href(), link.media(), link.type())));
        return sb.toString();
    }
}