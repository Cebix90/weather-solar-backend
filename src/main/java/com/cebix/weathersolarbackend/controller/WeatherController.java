package com.cebix.weathersolarbackend.controller;

import com.cebix.weathersolarbackend.model.WeatherForecastResponse;
import com.cebix.weathersolarbackend.model.WeatherSummaryResponse;
import com.cebix.weathersolarbackend.service.OpenMeteoService;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@Validated
@CrossOrigin
public class WeatherController {
    private final OpenMeteoService openMeteoService;

    public WeatherController(OpenMeteoService openMeteoService) {
        this.openMeteoService = openMeteoService;
    }

    @GetMapping("/forecast")
    public WeatherForecastResponse getForecast(
            @RequestParam @NotNull(message = "latitude is required")
            @DecimalMin(value = "-90.0", message = "latitude must be ≥ -90")
            @DecimalMax(value = "90.0",  message = "latitude must be ≤  90")
            Double latitude,

            @RequestParam @NotNull(message = "longitude is required")
            @DecimalMin(value = "-180.0", message = "longitude must be ≥ -180")
            @DecimalMax(value = "180.0",  message = "longitude must be ≤  180")
            Double longitude
    ) {
        return openMeteoService.getForecast(latitude, longitude);
    }

    @GetMapping("/summary")
    public WeatherSummaryResponse getSummary(
            @RequestParam @NotNull(message = "latitude is required")
            @DecimalMin(value = "-90.0", message = "latitude must be ≥ -90")
            @DecimalMax(value = "90.0",  message = "latitude must be ≤  90")
            Double latitude,

            @RequestParam @NotNull(message = "longitude is required")
            @DecimalMin(value = "-180.0", message = "longitude must be ≥ -180")
            @DecimalMax(value = "180.0",  message = "longitude must be ≤  180")
            Double longitude
    ) {
        return openMeteoService.getSummary(latitude, longitude);
    }
}
