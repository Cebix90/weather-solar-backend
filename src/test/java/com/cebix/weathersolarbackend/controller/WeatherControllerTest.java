//package com.cebix.weathersolarbackend.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(WeatherController.class)
//public class WeatherControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void shouldReturnForecast() throws Exception {
//        mockMvc.perform(get("/weather/forecast?latitude=50.0&longitude=20.0"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void shouldReturnSummary() throws Exception {
//        mockMvc.perform(get("/weather/summary?latitude=50.0&longitude=20.0"))
//                .andExpect(status().isOk());
//    }
//}