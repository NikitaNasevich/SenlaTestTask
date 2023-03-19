package com.senlainc.test.task.weatheranalyzer.controllers;

import com.senlainc.test.task.weatheranalyzer.dto.AvgTempRequest;
import com.senlainc.test.task.weatheranalyzer.dto.AvgTempResponse;
import com.senlainc.test.task.weatheranalyzer.dto.WeatherResponse;
import com.senlainc.test.task.weatheranalyzer.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current")
    public WeatherResponse getCurrentWeather() {
        return weatherService.findLastSavedWeather();
    }

    @GetMapping("/avg/temp")
    public AvgTempResponse getAvgTempForPeriod(@RequestBody AvgTempRequest weatherRequest) {
        return weatherService.findAvgTempForPeriod(weatherRequest);
    }
}
