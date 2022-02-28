package com.AcadianaPower.Controllers;


import com.AcadianaPower.Models.OutageModel;
import com.AcadianaPower.Services.OutageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@Controller
@RequestMapping(path = "/Outages")
public class OutageController {

    private final OutageService outageService;

    @Autowired
    public OutageController(OutageService outageService) {
        this.outageService = outageService;
    }

   // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<String> addOutage(@Valid @RequestBody OutageModel outage){
        outageService.addOutage(outage);
        return new ResponseEntity<String>("Outage created", HttpStatus.CREATED);
    }

    @GetMapping
    public List<OutageModel> getAllOutages(){
        return outageService.getAllOutages();
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

    @GetMapping("/byRecovery")
    public List<OutageModel> outagesByRecovery(){ return outageService.outagesByRecovery();}

    @GetMapping(path = "specificOutage/{zipCode}/{type}")
    public OutageModel getSpecificOutage(@PathVariable("zipCode") Integer zipCode,
                                         @PathVariable("type") String type) {
        return outageService.getSpecificOutage(zipCode,type);
    }


}
