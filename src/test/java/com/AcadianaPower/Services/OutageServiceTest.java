package com.AcadianaPower.Services;

import com.AcadianaPower.Models.OutageModel;
import com.AcadianaPower.Repositories.CustomerRepository;
import com.AcadianaPower.Repositories.OutageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

    @Captor ArgumentCaptor<Integer> zipArg;
    @Captor ArgumentCaptor<String> typeArg;
    @Captor ArgumentCaptor<OutageModel> outageArg;



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
        verify(testOutageRepository).save(outageArg.capture());
        assertEquals(outageArg.getValue(),testOutage);
    }

    @Test
    @DisplayName("Verify service: delete outage by zipcode,outage type")
    void deleteOutage() {
        testOutageService.deleteOutage(testOutage.getZipCode(),testOutage.getOutageType());
        verify(testOutageRepository).deleteOutage(zipArg.capture(),typeArg.capture());
        assertAll(
                () -> assertEquals(testOutage.getZipCode(),zipArg.getValue()),
                () -> assertEquals(testOutage.getOutageType(),typeArg.getValue())
        );
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
        verify(testOutageRepository).getOutagesByZip(zipArg.capture());
        assertEquals(testOutage.getZipCode(),zipArg.getValue());
    }

    @Test
    @DisplayName("Verify service: get outages by recovery time")
    void outagesByRecovery() {
        testOutageService.outagesByRecovery();
        verify(testOutageRepository).outagesByRecovery();
    }

    @Test
    @DisplayName("Verify service: get specific outage")
    void getSpecificOutage(){
        testOutageService.getSpecificOutage(testOutage.getZipCode(), testOutage.getOutageType());
        verify(testOutageRepository).getSpecificOutage(zipArg.capture(),typeArg.capture());
        assertAll(
                () -> assertEquals(testOutage.getZipCode(),zipArg.getValue()),
                () -> assertEquals(testOutage.getOutageType(),typeArg.getValue())
        );
    }
}