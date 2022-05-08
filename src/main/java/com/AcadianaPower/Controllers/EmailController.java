package com.AcadianaPower.Controllers;

import com.AcadianaPower.Models.EmailModel;
import com.AcadianaPower.Services.EmailService;
import com.AcadianaPower.Validation.ServiceValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@CrossOrigin(origins = "${angular.url}")
@RestController
@RequestMapping("/Email")
@AllArgsConstructor
public class EmailController {

    @Value("${spring.mail.username}")
    private String companyEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private final EmailService emailService;

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

       // Send confirmation
        emailService.sendEmail(emailModel.getSender(),
                "Report Confirmation",
                ServiceValidation.confirmationMessage());

    }
}
