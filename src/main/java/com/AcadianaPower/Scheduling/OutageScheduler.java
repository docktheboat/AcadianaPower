package com.AcadianaPower.Scheduling;


import com.AcadianaPower.Models.OutageModel;
import com.AcadianaPower.Services.OutageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class OutageScheduler {

    public final OutageService outageService;

    @Autowired
    public OutageScheduler(OutageService outageService){
        this.outageService = outageService;
    }

    @Scheduled(cron = "0 0/10 * * * *")
    public void deleteResolvedOutages(){
        List<OutageModel> schDelOutage = outageService.outagesByRecovery();
        for(OutageModel o : schDelOutage){
            if(o.getRecoveryTime().values().stream().reduce(0,Integer::sum) <= 0){
                outageService.deleteOutage(
                        o.getZipCode(),
                        o.getOutageType());
            } else { break; }
        }

    }
}
