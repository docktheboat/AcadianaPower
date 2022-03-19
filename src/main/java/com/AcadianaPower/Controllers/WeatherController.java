package com.AcadianaPower.Controllers;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;

@RestController
public class WeatherController {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/Weather")
    public WeatherStruct getWeather() {

        String url = "https://api.openweathermap.org/data/2.5/" +
                "weather?q=Lafayette,US-LA&appid=" + apiKey + "&units=imperial";

        String weatherInfo = restTemplate.getForObject(url, String.class);

        JSONObject main = new JSONObject(weatherInfo).getJSONObject("main");
        JSONArray weather = new JSONObject(weatherInfo).getJSONArray("weather");
        Object description = weather.getJSONObject(0).get("description");
        JSONObject wind = new JSONObject(weatherInfo).getJSONObject("wind");

        MathContext threeSig = new MathContext(3);

        return new WeatherStruct(
                BigDecimal.valueOf(Double.parseDouble(main.get("temp").toString())).round(threeSig),
                description.toString(),
                BigDecimal.valueOf(Double.parseDouble(wind.get("speed").toString())).round(threeSig));
    }

}

class WeatherStruct {
    public BigDecimal temperature;
    public String conditions;
    public BigDecimal windSpeed;
    public WeatherStruct(BigDecimal temperature,
                         String conditions,
                         BigDecimal windSpeed) {
        this.temperature = temperature;
        this.conditions = conditions;
        this.windSpeed = windSpeed;
    }
}

