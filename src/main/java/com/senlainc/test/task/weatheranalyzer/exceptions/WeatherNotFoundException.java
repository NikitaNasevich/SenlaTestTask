package com.senlainc.test.task.weatheranalyzer.exceptions;

public class WeatherNotFoundException extends RuntimeException {
    public WeatherNotFoundException(String message) {
        super(message);
    }
}
