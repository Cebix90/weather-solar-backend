package com.cebix.weathersolarbackend.service;

import com.cebix.weathersolarbackend.exception.ExternalApiException;
import com.cebix.weathersolarbackend.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenMeteoServiceImpl implements OpenMeteoService {

    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";

    private static final double SOLAR_POWER_KW = 2.5;
    private static final double PANEL_EFFICIENCY = 0.2;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherForecastResponse getForecast(double latitude, double longitude) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("daily",  "weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration")
                .queryParam("forecast_days", 7)
                .queryParam("timezone", "auto")
                .toUriString();

        try {
            OpenMeteoApiResponse apiResponse = restTemplate.getForObject(url, OpenMeteoApiResponse.class);
            if (apiResponse == null || apiResponse.getDaily() == null) {
                throw new ExternalApiException("No weather data returned from API");
            }
            return OpenMeteoMapper.mapToWeatherForecastResponse(apiResponse, SOLAR_POWER_KW, PANEL_EFFICIENCY);

        } catch (HttpClientErrorException e) {
            throw e;
        } catch (RestClientException e) {
            throw new ExternalApiException("Error calling weather API", e);
        }
    }

    @Override
    public WeatherSummaryResponse getSummary(double latitude, double longitude) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("daily",  "weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration")
                .queryParam("hourly", "pressure_msl")
                .queryParam("forecast_days", 7)
                .queryParam("timezone", "auto")
                .toUriString();

        try {
            OpenMeteoApiResponse apiResponse = restTemplate.getForObject(url, OpenMeteoApiResponse.class);

            if (apiResponse == null
                    || apiResponse.getDaily() == null
                    || apiResponse.getHourly() == null) {
                throw new ExternalApiException("Incomplete data returned from weather API");
            }

            return OpenMeteoMapper.mapToWeatherSummaryResponse(apiResponse);

        } catch (HttpClientErrorException e) {
            throw e;
        } catch (RestClientException e) {
            throw new ExternalApiException("Error calling weather API for summary", e);
        }
    }

}
