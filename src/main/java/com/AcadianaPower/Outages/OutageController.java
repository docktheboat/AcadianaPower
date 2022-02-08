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


    @DeleteMapping(path = "{id}")
    public void deleteOutage(@PathVariable("id") Long id){
        outageService.deleteOutage(id);
    }

    @GetMapping(path = "area/{zipCode}")
    public List<OutageModel> getOutagesByZipCode(@PathVariable("zipCode") Integer zipCode){
        return outageService.getOutagesByZipCode(zipCode);
    }

    @GetMapping(path = "type/{outageType}")
    public List<OutageModel> getOutagesByType(@PathVariable("outageType") String outageType) {
        return outageService.getOutagesByType(outageType);
    }

}
