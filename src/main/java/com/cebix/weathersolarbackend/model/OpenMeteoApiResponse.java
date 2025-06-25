package com.cebix.weathersolarbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OpenMeteoApiResponse {

    public Daily daily;
    public Hourly hourly;

    @Data
    public static class Daily {
        public List<String> time;
        public List<Integer> weather_code;
        public List<Double> temperature_2m_min;
        public List<Double> temperature_2m_max;
        public List<Double> sunshine_duration;
    }

    @Data
    public static class Hourly {
        public List<Double> pressure_msl;
    }
}
