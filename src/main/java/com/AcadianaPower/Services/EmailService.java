package com.AcadianaPower.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public static final String companyEmail = "acadianapowerandservices@gmail.com";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String sender, String receiver, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setText(text);
        message.setSubject(subject);
        javaMailSender.send(message);

    }
}
