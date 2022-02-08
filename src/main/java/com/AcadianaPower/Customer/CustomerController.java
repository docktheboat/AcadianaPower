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

    @GetMapping
    public List<CustomerModel> getAllCustomers(){
        return customerService.getAllCustomers();
    }

 /*   @GetMapping
    public List<CustomerModel> getAllCustomers(Model model){
        model.addAttribute("customers",customerService.getAllCustomers());
        return customerService.getAllCustomers();
    }*/

    @PostMapping
    public void addCustomer(@RequestBody CustomerModel customer){
        customerService.addCustomer(customer);
    }


    @DeleteMapping(path = "{idToDelete}")
    public void deleteCustomer(@PathVariable("idToDelete") Long idToDelete){
        customerService.deleteCustomer(idToDelete);
    }


    @GetMapping(path = "service/{lastname}")
    public List<CustomerModel> getCustomerByLastName(@PathVariable("lastname") String lastname){
        return customerService.getCustomerByLastName(lastname);
    }

    @GetMapping("/OauthTest")
    public String OauthTest(){
        return "Oauth test";
    }
}
