package com.AcadianaPower.Controllers;


import com.AcadianaPower.Models.MapModel;
import com.AcadianaPower.Services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Map")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService){
        this.mapService = mapService;
    }

    @GetMapping("/allInfo")
    public ResponseEntity<List<MapModel>> allMapInfo(){
        return new ResponseEntity<>(mapService.allMapInfo(), HttpStatus.OK);
    }

    @GetMapping("/infoByZip/{zipCode}")
    public ResponseEntity<List<MapModel>> tractByZip(@PathVariable("zipCode") Integer zipCode){
        return new ResponseEntity<>(mapService.tractByZip(zipCode),HttpStatus.OK);
    }

}
