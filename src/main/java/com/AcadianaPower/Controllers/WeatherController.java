package com.AcadianaPower.Controllers;

import com.AcadianaPower.Models.WeatherModel;
import com.AcadianaPower.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "${angular.url}")
public class WeatherController {

    public final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("/Weather")
    public ResponseEntity<WeatherModel> getWeather() {
        return new ResponseEntity<>(weatherService.getWeather(), HttpStatus.OK);
    }

}


