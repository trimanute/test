package com.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.model.WeatherData;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {
    private static final String WEATHER_API_URL = "http://api.weatherapi.com/v1/current.json";
    private static final String API_KEY = System.getenv("WEATHER_API_KEY");

    public WeatherData getWeatherByCity(String city) throws IOException {
        String url = WEATHER_API_URL + "?Key=" + API_KEY + "&q=" + city;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);
            
            String responseBody = EntityUtils.toString(response.getEntity());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            
            WeatherData weatherData = new WeatherData();
            
            // Extract location information
            // JsonNode locationNode = rootNode.path("location");
            // weatherData.setCity(locationNode.path("name").asText());
            // weatherData.setRegion(locationNode.path("region").asText());
            // weatherData.setCountry(locationNode.path("country").asText());
            // weatherData.setLocalTime(locationNode.path("localtime").asText());
            
            // Extract current weather information
            JsonNode currentNode = rootNode.path("current");
            weatherData.setTemperature(currentNode.path("temp_c").asDouble());
            weatherData.setHumidity(currentNode.path("humidity").asDouble());
            weatherData.setWindSpeed(currentNode.path("wind_kph").asDouble());
            weatherData.setWindDirection(currentNode.path("wind_dir").asText());
            // weatherData.setFeelsLike(currentNode.path("feelslike_c").asDouble());
            // weatherData.setWindDegree(currentNode.path("wind_degree").asDouble());
            
            // Extract condition information
            // JsonNode conditionNode = currentNode.path("condition");
            // weatherData.setCondition(conditionNode.path("text").asText());
            // weatherData.setConditionIcon(conditionNode.path("icon").asText());
            
            return weatherData;
        }
    }
}