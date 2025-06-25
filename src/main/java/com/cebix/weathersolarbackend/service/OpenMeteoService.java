package com.cebix.weathersolarbackend.service;

import com.cebix.weathersolarbackend.model.WeatherForecastResponse;
import com.cebix.weathersolarbackend.model.WeatherSummaryResponse;

public interface OpenMeteoService {
    WeatherForecastResponse getForecast(double latitude, double longitude);
    WeatherSummaryResponse getSummary(double latitude, double longitude);
}