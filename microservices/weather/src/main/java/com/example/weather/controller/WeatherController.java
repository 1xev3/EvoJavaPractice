package com.example.weather.controller;

import com.example.weather.model.Coord;
import com.example.weather.model.Main;
import com.example.weather.model.Root;
import com.example.weather.model.TimeCachedRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HashMap<Coord, TimeCachedRoot> cache;

    @Value("${appid}")
    private String appId;

    @Value("${url.weather}")
    private String urlWeather;

    @Value("${cache.duration}")
    private long cacheDuration;

    @GetMapping("/weather")
    public Main getWeather(@RequestParam double lat, @RequestParam double lon) {
        long currentTime = System.currentTimeMillis();
        Coord coordinates = new Coord(lon, lat);

        // cache
        if (cache.containsKey(coordinates)) {
            TimeCachedRoot cachedData = cache.get(coordinates);

            if (currentTime - cachedData.getTimestamp() < cacheDuration) {
                return cachedData.getRoot().getMain();
            }
        }

        // make request
        String request = String.format("%s?lat=%s&lon=%s&units=metric&appid=%s",
                urlWeather,
                lat,
                lon,
                appId
        );
        Root result = restTemplate.getForObject(request, Root.class);

        // cache it :)
        TimeCachedRoot cachedData = new TimeCachedRoot(result, currentTime);
        cache.put(coordinates, cachedData);

        return result.getMain();
    }
}
