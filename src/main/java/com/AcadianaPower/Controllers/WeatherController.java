package com.AcadianaPower.Controllers;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/Weather")
public class WeatherController {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("{zipCode}")
    public WeatherStruct getWeather(@PathVariable("zipCode") Integer zipCode){
     String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
                            zipCode + ",us&appid=" + apiKey + "&units=imperial";

     String weatherInfo = restTemplate.getForObject(url,String.class);

     JSONObject main = new JSONObject(weatherInfo).getJSONObject("main");
     JSONArray weather =  new JSONObject(weatherInfo).getJSONArray("weather");
     Object description = weather.getJSONObject(0).get("description");
     JSONObject wind = new JSONObject(weatherInfo).getJSONObject("wind");
     
        return new WeatherStruct(
                main.get("temp").toString() + " F",
                description.toString(),
                wind.get("speed").toString() + " mph");
    }
}

class WeatherStruct{
    public String temperature;
    public String conditions;
    public String windSpeed;
    public WeatherStruct(String temperature,
                         String conditions,
                         String windSpeed) {
        this.temperature = temperature;
        this.conditions = conditions;
        this.windSpeed = windSpeed;
    }
}
