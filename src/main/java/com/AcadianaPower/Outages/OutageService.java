package com.AcadianaPower.Outages;

import com.AcadianaPower.Customer.CustomerModel;
import com.AcadianaPower.Customer.CustomerService;
import com.AcadianaPower.Services.EmailService;
import com.AcadianaPower.Services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OutageService {

    private final CustomerService customerService;
    private final EmailService emailService;
    private final OutageRepository outageRepository;

    @Autowired
    public OutageService(CustomerService customerService, EmailService emailService, OutageRepository outageRepository) {
        this.customerService = customerService;
        this.emailService = emailService;
        this.outageRepository = outageRepository;
    }


    public void addOutage(OutageModel outage) {

        outage.setRecoveryTime(LocalDateTime.now().plusHours(2).plusMinutes(30));
        outageRepository.save(outage);

        Optional<List<CustomerModel>> customersAffected = Optional.of(
                customerService.getCustomersByZipCode(outage.getZipCode()).stream()
                .filter(customer -> customer.getServicesUsed().contains(outage.getOutageType())
                ).collect(Collectors.toList())
        );

        notifyOutage(customersAffected, outage);

    }

    public void deleteOutage(Long id) {
        if(outageRepository.existsById(id))
        outageRepository.deleteById(id);
    }

    public List<OutageModel> getAllOutages() {
        return outageRepository.findAll();
    }

    public List<OutageModel> getOutagesByType(String outageType){
        Optional<String> optionalType = Optional.ofNullable(outageType);
        if(Services.serviceCheck(outageType) && optionalType.isPresent()){
            ///////////////////////// current for testing purposes, change this later
            return outageRepository.findAll().stream().
                    filter(outage -> outage.getOutageType().equalsIgnoreCase(outageType))
                    .collect(Collectors.toList());

        }
        return List.of();
    }

    public List<OutageModel> getOutagesByZipCode(Integer zipCode){
        //if(outageRepository.getOutagesByZipCode(zipCode).isPresent()) {
        //return outageRepository.getOutagesByZipCode(zipCode).get();

        Optional<Integer> optionalZip = Optional.ofNullable(zipCode);
        if(optionalZip.isPresent() && String.valueOf(optionalZip.get()).length() == 5){
            ///////////////////////// current for testing purposes, change this later
            return outageRepository.findAll().stream().
                    filter(outage -> outage.getZipCode().equals(zipCode)).collect(Collectors.toList());

        }
        return List.of();
    }

 //   public void deleteAfterRecoveryEnd(){
        // if ( time )
                    //delete
    //}

    public void notifyOutage(Optional<List<CustomerModel>> affectedCustomers, OutageModel outage ) {
        Thread emailNotificationThread = new Thread(() -> affectedCustomers.ifPresent(
                customerModels -> customerModels.forEach(
                customer -> emailService.sendEmail(
                        EmailService.companyEmail,
                        customer.getEmail(),
                        "There Is an Outage In Your Area - Acadiana Power",
                        Services.outageMessage(outage.getOutageType(), outage.getRecoveryTime())

                ))));

        emailNotificationThread.setDaemon(true);
        emailNotificationThread.start();

    }

}