package com.AcadianaPower.Services;

import com.AcadianaPower.Models.OutageModel;
import com.AcadianaPower.Repositories.OutageRepository;
import com.AcadianaPower.Validation.ServiceValidation;
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

        if(outageRepository.getSpecificOutage(outage.getZipCode(), outage.getOutageType()).isPresent()){
            throw new IllegalArgumentException("That outage already exists");
        }

        outage.setRecoveryTime(LocalDateTime.now().plusHours(2).plusMinutes(30));
        outageRepository.save(outage);

        notifyOutage(
                Optional.of(customerService.
                        getAffectedCustomers(outage.getZipCode(),outage.getOutageType()))
                , outage.getOutageType()
                , outage.recoveryToString());

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

    public OutageModel getSpecificOutage(Integer zipCode, String type){
        return outageRepository.getSpecificOutage(zipCode,type).orElse(null);
    }

    public List<OutageModel> outagesByRecovery(){
        return outageRepository.outagesByRecovery().orElseGet(Arrays::asList); }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void notifyOutage(Optional<List<String>> affectedCustomers, String type, String time ) {
        String message = ServiceValidation.outageMessage(type, time);
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

    public List<OutageModel> outagesByCreation(){
        return outageRepository.outagesByCreation().orElseGet(Arrays::asList);
    }

}