package com.AcadianaPower.Services;

import com.AcadianaPower.Models.WeatherModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public WeatherModel getWeather() {

        String url = "https://api.openweathermap.org/data/2.5/" +
                "weather?q=Lafayette,US-LA&appid=" + apiKey + "&units=imperial";

        String weatherInfo = restTemplate.getForObject(url, String.class);

        JSONObject main = new JSONObject(weatherInfo).getJSONObject("main");
        JSONArray weather = new JSONObject(weatherInfo).getJSONArray("weather");
        Object description = weather.getJSONObject(0).get("description");
        JSONObject wind = new JSONObject(weatherInfo).getJSONObject("wind");

        MathContext threeSig = new MathContext(3);

        return new WeatherModel(
                BigDecimal.valueOf(Double.parseDouble(main.get("temp").toString())).round(threeSig),
                description.toString(),
                BigDecimal.valueOf(Double.parseDouble(wind.get("speed").toString())).round(threeSig));
    }

}
