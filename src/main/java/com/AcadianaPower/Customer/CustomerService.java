package com.AcadianaPower.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void addCustomer(CustomerModel customer){customerRepository.save(customer);}

    public List<CustomerModel> getAllCustomers(){ return customerRepository.findAll(); }

    @Transactional
    public void deleteCustomer(String email) {
        customerRepository.deleteCustomerByEmail(email);
    }

    public List<CustomerModel> getCustomersByZipCode(Integer zipCode){
        return customerRepository.getCustomersByZipCode(zipCode).orElseGet(List::of);
    }

    public CustomerModel getCustomerByEmail(String email){
        return customerRepository.getCustomerByEmail(email).orElse(null);
    }

    public List<String> getAffectedCustomers(Integer zipCode, String outageType){
        return customerRepository.customersAffectedByOutage(zipCode,outageType).orElseGet(List::of);
    }
}
