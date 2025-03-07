package com.weather.controller;

import com.weather.model.WeatherData;
import com.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*") // Allow cross-origin requests
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping("/{city}")
    public ResponseEntity<WeatherData> getWeatherByCity(@PathVariable String city) {
        try {
            WeatherData weatherData = weatherService.getWeatherByCity(city);
            return ResponseEntity.ok(weatherData);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}