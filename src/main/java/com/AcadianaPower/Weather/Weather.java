package com.AcadianaPower.Weather;


import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/Weather")
public class Weather {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("{zipCode}")
    public String getWeather(@PathVariable("zipCode") Integer zipCode){
      /*  String strZipCode = String.valueOf(zipCode);

     String url = "http://api.openweathermap.org/data/2.5/weather?zip=" +
                            strZipCode + ",us&appid=" + apiKey + "&units=imperial";

     String weatherInfo = restTemplate.getForObject(url,String.class);
     //return new JSONObject(weatherInfo).getJSONObject("main").getString("temp");
        JSONObject jsonObject = new JSONObject(weatherInfo).getJSONObject("main");

        return jsonObject.get("temp").toString(); */

       return "it works";

    }
}
