package com.AcadianaPower.Outages;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addOutage(@RequestBody OutageModel outage){
        outageService.addOutage(outage);
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
    public List<OutageModel> outagesByRecovery(){
        return outageService.outagesByRecovery();
    }


}
