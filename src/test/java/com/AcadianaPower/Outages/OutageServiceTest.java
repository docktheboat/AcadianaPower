package com.AcadianaPower.Outages;

import com.AcadianaPower.Customer.CustomerRepository;
import com.AcadianaPower.Customer.CustomerService;
import com.AcadianaPower.Services.EmailService;
import com.AcadianaPower.Services.SmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OutageServiceTest {

    @Mock
    private OutageRepository testOutageRepository;
    private OutageService testOutageService;

    @Mock
    private CustomerRepository testCustomerRepository;

    private OutageModel testOutage;


    @BeforeEach
    void init(){
        testOutage = new OutageModel(
              "GAS",
              70506
        );
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
        testOutageService.addOutage(testOutage);

        ArgumentCaptor<OutageModel> oArg =
                ArgumentCaptor.forClass(OutageModel.class);

        verify(testOutageRepository).save(oArg.capture());

        assertEquals(oArg.getValue(),testOutage);
    }

    @Test
    @DisplayName("Verify service: delete outage by zipcode,outage type")
    void deleteOutage() {
        testOutageService.deleteOutage(testOutage.getZipCode(),testOutage.getOutageType());
        verify(testOutageRepository).deleteOutage(testOutage.getZipCode(),testOutage.getOutageType());
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
        testOutageService.getOutagesByZipCode(testOutage.getZipCode());
        verify(testOutageRepository).getOutagesByZip(testOutage.getZipCode());
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
                testOutage.getOutageType(),
                "test time"
        );
    }
}