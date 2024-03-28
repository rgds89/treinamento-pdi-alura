package br.com.alura.adopet.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;
    private final static String EMAIL_FROM = "adopet@email.com.br";

    public void enviar(String emailTo, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(EMAIL_FROM);
        email.setTo(emailTo);
        email.setSubject(assunto);
        email.setText(mensagem);
        emailSender.send(email);
    }
}
