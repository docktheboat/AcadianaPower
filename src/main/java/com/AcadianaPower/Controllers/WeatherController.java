package com.AcadianaPower.Controllers;


import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@RestController
@RequestMapping("/Weather")
public class WeatherController {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("{zipCode}")
    public List<String> getWeather(@PathVariable("zipCode") Integer zipCode){
       String strZipCode = String.valueOf(zipCode);

     String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
                            strZipCode + ",us&appid=" + apiKey + "&units=imperial";
     String weatherInfo = restTemplate.getForObject(url,String.class);

        String advisoriesURL = "https://api.weather.gov/alerts/active?area=LA";
        String advisories = restTemplate.getForObject(advisoriesURL, String.class);


        JSONObject main = new JSONObject(weatherInfo).getJSONObject("main");
        JSONArray weather =  new JSONObject(weatherInfo).getJSONArray("weather");
        Object description = weather.getJSONObject(0).get("description");
        JSONObject wind = new JSONObject(weatherInfo).getJSONObject("wind");


        List<String> weatherData = new ArrayList<>(Arrays.asList(
                "Temperature: " + main.get("temp").toString() + " F",
                "Conditions: " + description.toString(),
                "Wind Speed: " + wind.get("speed").toString() + " mph"
        ));

        JSONObject advisoriesObj = new JSONObject(advisories);
        JSONArray features = advisoriesObj.getJSONArray("features");
        for (int i = 0; i < features.length(); i++) {
            JSONObject properties = features.getJSONObject(i).getJSONObject("properties");
            weatherData.add("Advisory "+ (i+1) +": " + properties.getString("headline"));

        }
        return weatherData;

    }
}
