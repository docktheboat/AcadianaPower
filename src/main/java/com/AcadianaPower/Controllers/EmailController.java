package com.AcadianaPower.Controllers;

import com.AcadianaPower.Models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "${angular.url}")
@RestController
@RequestMapping("/Email")
public class EmailController {

    @Value("${spring.mail.username}")
    private String companyEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailModel emailModel){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailModel.getSender());
        message.setTo(companyEmail);
        message.setText("From " + emailModel.getSender() +": " +emailModel.getMessage());
        message.setSubject("Outage Report " +
                emailModel.getService() + " "
                + emailModel.getZipCode());
        javaMailSender.send(message);


    }
}
