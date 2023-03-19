package com.senlainc.test.task.weatheranalyzer.exceptions;

public class WeatherForPeriodNotFoundException extends RuntimeException {
    public WeatherForPeriodNotFoundException(String message) {
        super(message);
    }
}
