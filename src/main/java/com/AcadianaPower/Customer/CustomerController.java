package com.AcadianaPower.Customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Customer")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/allCustomers")
    public List<CustomerModel> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/newCustomer")
    public void addCustomer(@RequestBody CustomerModel customer){
        customerService.addCustomer(customer);
    }

    @DeleteMapping(path = "deleteCustomer/{emailToDelete}")
    public void deleteCustomer(@PathVariable("emailToDelete") String emailToDelete){
        customerService.deleteCustomer(emailToDelete);
    }

    @GetMapping("customerByEmail/{email}")
    public CustomerModel getCustomerByEmail(@PathVariable("email") String email) {
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/OauthTest")
    public String OauthTest(){
        return "Oauth test";
    }
}
