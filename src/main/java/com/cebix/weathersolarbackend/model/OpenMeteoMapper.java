package com.cebix.weathersolarbackend.model;

import java.util.ArrayList;
import java.util.List;

public class OpenMeteoMapper {
    public static WeatherForecastResponse mapToWeatherForecastResponse(OpenMeteoApiResponse apiResponse, double powerKw, double efficiency) {
        List<WeatherDayData> days = new ArrayList<>();
        if (apiResponse != null && apiResponse.daily != null) {
            for (int i = 0; i < apiResponse.daily.time.size(); i++) {
                double sunshineHours = apiResponse.daily.sunshine_duration.get(i) / 3600.0;
                double estimatedEnergy = powerKw * sunshineHours * efficiency;
                days.add(new WeatherDayData(
                        apiResponse.daily.time.get(i),
                        apiResponse.daily.weather_code.get(i),
                        apiResponse.daily.temperature_2m_min.get(i),
                        apiResponse.daily.temperature_2m_max.get(i),
                        Math.round(estimatedEnergy * 100.0) / 100.0
                ));
            }
        }
        return new WeatherForecastResponse(days);
    }

    public static WeatherSummaryResponse mapToWeatherSummaryResponse(OpenMeteoApiResponse api) {
        if (api == null || api.daily == null || api.hourly == null) {
            return WeatherSummaryResponse.dummy();
        }

        double avgPressure = api.hourly.pressure_msl.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        double avgSun = api.daily.sunshine_duration.stream()
                .mapToDouble(d -> d / 3600.0)
                .average()
                .orElse(0.0);

        double minTemp = api.daily.temperature_2m_min.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0.0);

        double maxTemp = api.daily.temperature_2m_max.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);

        long rainyDays = api.daily.weather_code.stream()
                .filter(OpenMeteoMapper::isRainyCode)
                .count();
        String summary = rainyDays >= 4 ? "z opadami" : "bez opadów";

        return new WeatherSummaryResponse(
                Math.round(avgPressure * 100.0) / 100.0,
                Math.round(avgSun * 100.0) / 100.0,
                minTemp,
                maxTemp,
                summary
        );
    }

    private static boolean isRainyCode(int code) {
        return code >= 51 && code <= 99;
    }
}
