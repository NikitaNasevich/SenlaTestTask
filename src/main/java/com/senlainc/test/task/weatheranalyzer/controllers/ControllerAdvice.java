package com.senlainc.test.task.weatheranalyzer.controllers;

import com.senlainc.test.task.weatheranalyzer.exceptions.WeatherErrorResponse;
import com.senlainc.test.task.weatheranalyzer.exceptions.WeatherForPeriodNotFoundException;
import com.senlainc.test.task.weatheranalyzer.exceptions.WeatherNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(WeatherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public WeatherErrorResponse handleWeatherNotParsed(WeatherNotFoundException weatherNotFoundException) {
        log.error(weatherNotFoundException.getMessage());
        return new WeatherErrorResponse(weatherNotFoundException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(WeatherForPeriodNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WeatherErrorResponse handleWeatherNotParsed(WeatherForPeriodNotFoundException weatherForPeriodNotFoundException) {
        log.error(weatherForPeriodNotFoundException.getMessage());
        return new WeatherErrorResponse(weatherForPeriodNotFoundException.getMessage(), LocalDateTime.now());
    }
}
