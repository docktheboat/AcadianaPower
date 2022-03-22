package com.AcadianaPower.Services;

import com.AcadianaPower.Models.CustomerModel;
import com.AcadianaPower.Repositories.CustomerRepository;
import com.AcadianaPower.Services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository testCustomerRepository;
    private CustomerService testCustomerService;

    private CustomerModel testCustomer;

    @Captor ArgumentCaptor<CustomerModel> customerArg;
    @Captor ArgumentCaptor<String> strArg;
    @Captor ArgumentCaptor<Integer> zipArg;


    @BeforeEach
    void init(){
        testCustomer =  new CustomerModel(
                "Walsh",
                "707 Memory Lane",
                70503,
                "7775557777",
                "INTERNET",
                "testEmail@test.com"
        );

        testCustomerService = new CustomerService(testCustomerRepository);
    }

    @Test
    @DisplayName("Verify service: add customer")
    void addCustomer() {
        testCustomerService.addCustomer(testCustomer);
        verify(testCustomerRepository).save(customerArg.capture());
        assertEquals(customerArg.getValue(),testCustomer);
    }

    @Test
    @DisplayName("Verify service: get all customers")
    void getAllCustomers() {
        testCustomerService.getAllCustomers();
        verify(testCustomerRepository).findAll();
    }

    @Test
    @DisplayName("Verify service: delete customer by email")
    void deleteCustomer() {
        testCustomerService.deleteCustomer(testCustomer.getEmail());
        verify(testCustomerRepository).deleteCustomerByEmail(strArg.capture());
        assertEquals(testCustomer.getEmail(),strArg.getValue());
    }

    @Test
    @DisplayName("Verify service: get customer by zipcode")
    void getCustomersByZipCode() {
        testCustomerService.getCustomersByZipCode(testCustomer.getZipCode());
        verify(testCustomerRepository).getCustomersByZipCode(zipArg.capture());
        assertEquals(testCustomer.getZipCode(),zipArg.getValue());
    }

    @Test
    @DisplayName("Verify service: delete customer by email")
    void getCustomerByEmail() {
        testCustomerService.getCustomerByEmail(testCustomer.getEmail());
        verify(testCustomerRepository).getCustomerByEmail(strArg.capture());
        assertEquals(testCustomer.getEmail(),strArg.getValue());
    }

    @Test
    @DisplayName("Verify service: customers affected by new outage")
    void getAffectedCustomers() {
        testCustomerService.getAffectedCustomers(
                testCustomer.getZipCode(),testCustomer.getServicesUsed());
        verify(testCustomerRepository)
                .customersAffectedByNewOutage(zipArg.capture(),
                        strArg.capture());
        assertAll(
                () -> assertEquals(testCustomer.getZipCode(),zipArg.getValue()),
                () -> assertEquals(testCustomer.getServicesUsed(),strArg.getValue())
        );
    }

}