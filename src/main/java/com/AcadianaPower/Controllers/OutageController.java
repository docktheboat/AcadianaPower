package com.AcadianaPower.Controllers;


import com.AcadianaPower.Models.OutageModel;
import com.AcadianaPower.Services.OutageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "${angular.url}")
@RestController
@RequestMapping(path = "/Outages")
public class OutageController {

    private final OutageService outageService;

    @Autowired
    public OutageController(OutageService outageService) {
        this.outageService = outageService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addOutage(@Valid @RequestBody OutageModel outage){
        try{
            outageService.addOutage(outage);
        }catch(IllegalArgumentException iae){
            return new ResponseEntity<String>("That outage already exists",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<String>("Outage created", HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OutageModel>> getAllOutages(){
        return new ResponseEntity<>(outageService.getAllOutages(), HttpStatus.OK);
    }

    @DeleteMapping(path = "deleteOutage/{zipCode}/{type}")
    public void deleteOutage(@PathVariable("zipCode") Integer zipCode,
                             @PathVariable("type") String type) {
        outageService.deleteOutage(zipCode,type);
    }

    @GetMapping(path = "area/{zipCode}")
    public List<OutageModel> getOutagesByZipCode(@PathVariable("zipCode") Integer zipCode){
        return outageService.getOutagesByZipCode(zipCode);
    }

    @GetMapping("/getByRecovery")
    public List<OutageModel> outagesByRecovery(){ return outageService.outagesByRecovery();}

    @GetMapping(path = "specificOutage/{zipCode}/{type}")
    public ResponseEntity<OutageModel> getSpecificOutage(@PathVariable("zipCode") Integer zipCode,
                                         @PathVariable("type") String type) {
        return new ResponseEntity<>(outageService.getSpecificOutage(zipCode,type),
                HttpStatus.OK);
    }

    @GetMapping("/getByCreation")
    public ResponseEntity<List<OutageModel>> outagesByCreation(){
        return new ResponseEntity<>(outageService.outagesByCreation(),HttpStatus.OK);
    }


}
