package com.cebix.weathersolarbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDayData {
    private String date;
    private int weatherCode;
    private double minTemp;
    private double maxTemp;
    private double estimatedEnergy;
}
