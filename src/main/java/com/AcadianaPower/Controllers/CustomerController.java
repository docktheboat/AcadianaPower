package com.AcadianaPower.Controllers;


import com.AcadianaPower.Models.CustomerModel;
import com.AcadianaPower.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerModel customer){
        try {
            customerService.addCustomer(customer);
        }catch(IllegalArgumentException iae){
            return new ResponseEntity<String>("Please use a different email account",
                    HttpStatus.UNPROCESSABLE_ENTITY);

        }
        return new ResponseEntity<String>("Account Created", HttpStatus.CREATED);
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

    @GetMapping("/allCurrentlyAffected")
    public List<String> customersAffectedAllCurrentOutages(){
        return customerService.customersAffectedAllCurrentOutages();
    }

    @GetMapping("/customersByZip/{zipCode}")
    public List<CustomerModel> getCustomersByZipCode(@PathVariable("zipCode") Integer zipCode){
        return customerService.getCustomersByZipCode(zipCode);
    }
}
