package com.cebix.weathersolarbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherSummaryResponse {
    private double averagePressure;
    private double averageSunlightHours;
    private double minTemp;
    private double maxTemp;
    private String weekSummary;

    //TODO to be removed
    public static WeatherSummaryResponse dummy() {
        return new WeatherSummaryResponse(1013.25, 7.5, 12.0, 27.0, "z opadami");
    }
}
