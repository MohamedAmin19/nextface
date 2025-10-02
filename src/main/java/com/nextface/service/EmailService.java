package com.nextface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final JavaMailSender mailSender;

    public void sendReservationEmail(String to, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("reservations@nextfaceofficial.com");
        message.setTo(to);
        message.setSubject("Welcome to Next Face");
        message.setText("Hi " + name + ",\n\nThank you for registering with NextFace! "
                + "Your reservation has been successfully created.\n\n"
                + "Best regards,\nNextFace Team");

        mailSender.send(message);
    }
}
