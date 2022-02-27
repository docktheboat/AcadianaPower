package com.AcadianaPower.Services;

import com.AcadianaPower.Models.CustomerModel;
import com.AcadianaPower.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public String addCustomer(CustomerModel customer){
        customerRepository.save(customer);
        return "New account created";
    }

    public List<CustomerModel> getAllCustomers(){ return customerRepository.findAll(); }

    @Transactional
    public void deleteCustomer(String email) {
        customerRepository.deleteCustomerByEmail(email);
    }

    public List<CustomerModel> getCustomersByZipCode(Integer zipCode){
        return customerRepository.getCustomersByZipCode(zipCode).orElseGet(Arrays::asList);
    }

    public CustomerModel getCustomerByEmail(String email){
        return customerRepository.getCustomerByEmail(email).orElse(null);
    }

    public List<String> getAffectedCustomers(Integer zipCode, String outageType){
        return customerRepository.customersAffectedByNewOutage(zipCode,outageType).orElseGet(Arrays::asList);
    }

    public List<String> customersAffectedAllCurrentOutages(){
        return customerRepository.customersAffectedAllOutages().orElseGet(Arrays::asList);
    }
}
