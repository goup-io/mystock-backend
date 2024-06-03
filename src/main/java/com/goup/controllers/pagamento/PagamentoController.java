package com.goup.controllers.pagamento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pix.DadosEnvioPix;
import pix.QRCodePix;

import java.math.BigDecimal;
import java.nio.file.Path;

@RestController
public class PagamentoController {

    @GetMapping("/generatePixQRCode")
    public String generatePixQRCode() {
        final var imagePath = "qrcode.png";

        final var dadosPix =
                new DadosEnvioPix(
                        "João Pedro Noleto", "38637406882",
                        new BigDecimal("1.0"), "Palmas", "PIX em Java");

        final var qrCodePix = new QRCodePix(dadosPix);
        qrCodePix.save(Path.of(imagePath));

        return "QRCode generated and saved at " + qrCodePix + generatePixQRCode();
    }


    @GetMapping("/generatePixQRCodeBASE64")
    public String generatePixQRCodeBASE64() {
        final var imagePath = "qrcode.png";

        final var dadosPix =
                new DadosEnvioPix(
                        "João Pedro Noleto", "38637406882",
                        new BigDecimal("1.0"), "Palmas", "PIX em Java");

        final var qrCodePix = new QRCodePix(dadosPix);
        String base64Image = qrCodePix.saveAndGetBytesAsBase64(Path.of(imagePath));

        return base64Image;
    }

}