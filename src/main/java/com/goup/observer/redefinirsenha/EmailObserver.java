package com.goup.observer.redefinirsenha;

import com.goup.observer.Observer;
import com.goup.services.email.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailObserver implements Observer {

    private final EmailService emailService;

    public EmailObserver(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void enviar(String email, String nome) {
        String assunto = "Redefinição de Senha";
        String corpo = "Olá  " + nome + "! Você solicitou a redifinição da sua senha? \n" +
                "Clique no link abaixo para redefinir sua senha. Caso não tenha sido você, ignore este e-mail. \n" +
                "https://www.goup.com.br/redefinir-senha";

        emailService.enviarEmail(email, assunto, corpo);
    }
}
