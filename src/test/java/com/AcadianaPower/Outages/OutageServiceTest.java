package com.AcadianaPower.Outages;

import com.AcadianaPower.Customer.CustomerRepository;
import com.AcadianaPower.Customer.CustomerService;
import com.AcadianaPower.Services.EmailService;
import com.AcadianaPower.Services.SmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OutageServiceTest {

    @Mock
    private OutageRepository testOutageRepository;
    private OutageService testOutageService;

    @Mock
    private CustomerRepository testCustomerRepository;


    @BeforeEach
    void init(){
        testOutageService = new OutageService(
                new CustomerService(testCustomerRepository),
                new EmailService(),
                new SmsService(),
                testOutageRepository
            );
    }

    @Test
    @DisplayName("Verify service: add outage")
    void addOutage() {
        OutageModel outage = new OutageModel();
        testOutageService.addOutage(outage);
        verify(testOutageRepository).save(outage);
    }

    @Test
    @DisplayName("Verify service: delete outage by zipcode,outage type")
    void deleteOutage() {
        testOutageService.deleteOutage(70506,"GAS");
        verify(testOutageRepository).deleteOutage(70506,"GAS");
    }

    @Test
    @DisplayName("Verify service: get all outages")
    void getAllOutages() {
        testOutageService.getAllOutages();
        verify(testOutageRepository).findAll();
    }

    @Test
    @DisplayName("Verify service: get outages by zipcode")
    void getOutagesByZipCode() {
        testOutageService.getOutagesByZipCode(70506);
        verify(testOutageRepository).getOutagesByZip(70506);
    }

    @Test
    @DisplayName("Verify service: get outages by recovery time")
    void outagesByRecovery() {
        testOutageService.outagesByRecovery();
        verify(testOutageRepository).outagesByRecovery();
    }

    @Test
    @DisplayName("Verify service: notify outage")
    void notifyOutage() {
        testOutageService.notifyOutage(
                Optional.of(Collections.emptyList()),
                "GAS",
                "test time"
        );
    }
}