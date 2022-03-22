package com.AcadianaPower.Controllers;

import com.AcadianaPower.Models.EmailModel;
import com.AcadianaPower.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${angular.url}")
@RestController
@RequestMapping("/Email")
public class EmailController {


    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailModel emailModel){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailService.companyEmail);
        message.setTo(EmailService.companyEmail);
        message.setText("From " + emailModel.getSender() +": " +emailModel.getMessage());
        message.setSubject("Outage Report " +
                emailModel.getService() + " "
                + emailModel.getZipCode());
        javaMailSender.send(message);
    }
}
