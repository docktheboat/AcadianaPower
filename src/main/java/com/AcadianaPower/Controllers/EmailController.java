package com.AcadianaPower.Controllers;

import com.AcadianaPower.Models.EmailModel;
import com.AcadianaPower.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Email")
public class EmailController {

    public final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }


   /* @PostMapping("/send")
    public void reportOutage(@RequestBody EmailModel emailModel){
        emailService.sendEmail(
                emailModel.getSender(),
                EmailService.companyEmail,
                "Outage Report (" + emailModel.getService() +"," + emailModel.getZipCode()+")",
                emailModel.getMessage()
        );
    }*/

    @GetMapping("/send")
    public void reportOutage(){
        emailService.sendEmail(
                EmailService.companyEmail,
                EmailService.companyEmail,
                "Outage Report",
                "hey"
        );
    }
}
