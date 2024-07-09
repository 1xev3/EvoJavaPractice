package com.example.weather.config;

import com.example.weather.model.Coord;
import com.example.weather.model.Root;
import com.example.weather.model.TimeCachedRoot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Configuration
public class WeatherConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HashMap<Coord, TimeCachedRoot> getCache() {
        return new HashMap<Coord, TimeCachedRoot>();
    }
}
