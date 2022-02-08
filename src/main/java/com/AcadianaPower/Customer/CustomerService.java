package com.AcadianaPower.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void addCustomer(CustomerModel customer){

        customerRepository.save(customer);
    }

    public List<CustomerModel> getAllCustomers(){ return customerRepository.findAll(); }

    @Transactional
    public void updateEmail(String email){}

    @Transactional
    public void updatePhoneNumber(String phoneNumber){}

    @Transactional
    public void updateAddress(String address){}

   /* @Transactional
    public void addService(String service, Long id) {
        if (customerRepository.existsById(id)) {
            CustomerModel customer = customerRepository.getById(id);
            if(service != null){
                customer.setService(service);
        }
            // else invalid input or service is not offered

    }
        // else customer does not exist
    }*/

    // Button to delete
    public void deleteService(){

    }

    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        // should not be able to delete their account for now
    }

    public List<CustomerModel> getCustomerByLastName(String lastname){
        Optional<String> optionalService = Optional.ofNullable(lastname);
        if(optionalService.isPresent() && optionalService.get().length() > 0){
            return customerRepository.findAll().stream().filter(service ->
                     service.getLastName().equalsIgnoreCase(lastname))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public List<CustomerModel> getCustomersByZipCode(Integer zipCode){
        Optional<Integer> optionalInteger = Optional.ofNullable(zipCode);
        if (optionalInteger.isPresent()){
            return customerRepository.findAll().stream().filter(
                    customer -> customer.getZipCode().equals(zipCode)
            ).collect(Collectors.toList());
        }
        return List.of();
    }





}
