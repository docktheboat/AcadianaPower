package com.AcadianaPower.Controllers;


import com.AcadianaPower.Models.CustomerModel;
import com.AcadianaPower.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "${angular.url}")
@RestController
@RequestMapping(path = "/Customer")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<List<CustomerModel>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
    }

    @PostMapping("/newCustomer")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerModel customer){
        try {
            customerService.addCustomer(customer);
        }catch(IllegalArgumentException iae){
            return new ResponseEntity<String>("Please use a different email account",
                    HttpStatus.UNPROCESSABLE_ENTITY);

        }
        return new ResponseEntity<>("Account Created", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteCustomer/{emailToDelete}")
    public void deleteCustomer(@PathVariable("emailToDelete") String emailToDelete){
        customerService.deleteCustomer(emailToDelete);
    }

    @GetMapping("/customerByEmail/{email}")
    public ResponseEntity<CustomerModel> getCustomerByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(customerService.getCustomerByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/customersByZip/{zipCode}")
    public ResponseEntity<List<CustomerModel>> getCustomersByZipCode(@PathVariable("zipCode") Integer zipCode){
        return new ResponseEntity<>(customerService.getCustomersByZipCode(zipCode),HttpStatus.OK);
    }
}
