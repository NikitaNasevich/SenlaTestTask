package com.senlainc.test.task.weatheranalyzer.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeatherErrorResponse {
    private String message;
    private LocalDateTime timestamp;

    public WeatherErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
