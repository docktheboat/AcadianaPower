package com.AcadianaPower.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherModel {
    private BigDecimal temperature;
    private String conditions;
    private BigDecimal windSpeed;
}
