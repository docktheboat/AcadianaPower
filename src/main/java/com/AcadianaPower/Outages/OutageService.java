package com.AcadianaPower.Outages;

import com.AcadianaPower.Customer.CustomerService;
import com.AcadianaPower.Services.EmailService;
import com.AcadianaPower.Services.Services;
import com.AcadianaPower.Services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OutageService {

    private final CustomerService customerService;
    private final EmailService emailService;
    private final SmsService smsService;
    private final OutageRepository outageRepository;

    @Autowired
    public OutageService(
            CustomerService customerService,
            EmailService emailService,
            SmsService smsService,
            OutageRepository outageRepository
    ) {
        this.customerService = customerService;
        this.emailService = emailService;
        this.smsService = smsService;
        this.outageRepository = outageRepository;
    }


    public void addOutage(OutageModel outage) {

        outage.setRecoveryTime(LocalDateTime.now().plusHours(2).plusMinutes(30));
        outageRepository.save(outage);

        notifyOutage(
                Optional.of(customerService.
                        getAffectedCustomers(outage.getZipCode(),outage.getOutageType()))
                , outage.getOutageType()
                , outage.getRecoveryTime());

    }

    @Transactional
    public void deleteOutage(Integer zipCode, String type) {
        outageRepository.deleteOutage(zipCode,type);
    }

    public List<OutageModel> getAllOutages() {
        return outageRepository.findAll();
    }

    public List<OutageModel> getOutagesByZipCode(Integer zipCode) {
        return outageRepository.getOutagesByZip(zipCode).orElseGet(Arrays::asList);
    }

    public List<OutageModel> outagesByRecovery(){
        return outageRepository.outagesByRecovery().orElseGet(Arrays::asList); }

    public void notifyOutage(Optional<List<String>> affectedCustomers, String type, String time ) {
        String message = Services.outageMessage(type, time);
        Thread NotificationThread = new Thread(() -> affectedCustomers.ifPresent(
                customers -> customers.forEach(
                        customerInfo -> {
                            String[] emailAndPhone = customerInfo.split(",");
                            emailService.sendEmail(
                                    EmailService.companyEmail,
                                    emailAndPhone[0],
                                    "There Is an Outage In Your Area - Acadiana Power",
                                    message

                            );
                            smsService.smsNotifyOutage(emailAndPhone[1], message);
                        }
                            )));

        NotificationThread.setDaemon(true);
        NotificationThread.start();

    }

}