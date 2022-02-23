package com.AcadianaPower.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository testCustomerRepository;
    private CustomerService testCustomerService;

    @BeforeEach
    void init(){
        testCustomerService = new CustomerService(testCustomerRepository);
    }

    @Test
    @DisplayName("Verify service: add customer")
    void addCustomer() {
        CustomerModel customer = new CustomerModel();
        testCustomerService.addCustomer(customer);
        verify(testCustomerRepository).save(customer);
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
        testCustomerService.deleteCustomer("testEmail");
        verify(testCustomerRepository).deleteCustomerByEmail("testEmail");

    }

    @Test
    @DisplayName("Verify service: get customer by zipcode")
    void getCustomersByZipCode() {
        testCustomerService.getCustomersByZipCode(70506);
        verify(testCustomerRepository).getCustomersByZipCode(70506);
    }

    @Test
    @DisplayName("Verify service: delete customer by email")
    void getCustomerByEmail() {
        testCustomerService.getCustomerByEmail("testEmail");
        verify(testCustomerRepository).getCustomerByEmail("testEmail");
    }

    @Test
    @DisplayName("Verify service: customers affected by new outage")
    void getAffectedCustomers() {
        testCustomerService.getAffectedCustomers(70506,"GAS");
        verify(testCustomerRepository)
                .customersAffectedByNewOutage(70506,"GAS");
    }

    @Test
    @DisplayName("Verify service: customers affected by all current outages")
    void customersAffectedAllCurrentOutages() {
        testCustomerService.customersAffectedAllCurrentOutages();
        verify(testCustomerRepository).customersAffectedAllOutages();
    }
}