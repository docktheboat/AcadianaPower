package com.AcadianaPower.Services;

import com.AcadianaPower.Models.CustomerModel;
import com.AcadianaPower.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void addCustomer(CustomerModel customer){
        if(customerRepository.getCustomerByEmail(customer.getEmail()).isPresent()){
            throw new IllegalArgumentException("An account with that email already exists");
        }
        customerRepository.save(customer);
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
