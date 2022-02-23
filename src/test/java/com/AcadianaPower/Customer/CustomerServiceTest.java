package com.AcadianaPower.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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


    @BeforeEach
    void init(){
        testCustomer =  new CustomerModel(
                "Mandy",
                "Walsh",
                "707 Memory Lane",
                70503,
                LocalDate.of(1982, Month.AUGUST,22),
                "777-777-7777",
                "GAS",
                "testEmail@test.com"
        );

        testCustomerService = new CustomerService(testCustomerRepository);
    }

    @Test
    @DisplayName("Verify service: add customer")
    void addCustomer() {
        testCustomerService.addCustomer(testCustomer);

        ArgumentCaptor<CustomerModel> cArg =
                ArgumentCaptor.forClass(CustomerModel.class);

        verify(testCustomerRepository).save(
                cArg.capture());

        assertEquals(cArg.getValue(),testCustomer);
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
        verify(testCustomerRepository).deleteCustomerByEmail(testCustomer.getEmail());
    }

    @Test
    @DisplayName("Verify service: get customer by zipcode")
    void getCustomersByZipCode() {
        testCustomerService.getCustomersByZipCode(testCustomer.getZipCode());
        verify(testCustomerRepository).getCustomersByZipCode(testCustomer.getZipCode());
    }

    @Test
    @DisplayName("Verify service: delete customer by email")
    void getCustomerByEmail() {
        testCustomerService.getCustomerByEmail(testCustomer.getEmail());
        verify(testCustomerRepository).getCustomerByEmail(testCustomer.getEmail());
    }

    @Test
    @DisplayName("Verify service: customers affected by new outage")
    void getAffectedCustomers() {
        testCustomerService.getAffectedCustomers(
                testCustomer.getZipCode(),testCustomer.getServicesUsed());
        verify(testCustomerRepository)
                .customersAffectedByNewOutage(testCustomer.getZipCode(),
                        testCustomer.getServicesUsed());
    }

    @Test
    @DisplayName("Verify service: customers affected by all current outages")
    void customersAffectedAllCurrentOutages() {
        testCustomerService.customersAffectedAllCurrentOutages();
        verify(testCustomerRepository).customersAffectedAllOutages();
    }
}