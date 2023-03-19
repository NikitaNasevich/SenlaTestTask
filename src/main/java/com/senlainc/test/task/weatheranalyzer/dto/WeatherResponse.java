package com.senlainc.test.task.weatheranalyzer.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private double temp;
    private double windSpeedKph;
    private double pressureMilliBars;
    private int humidity;
    private String condition;
    private String location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherResponse response = (WeatherResponse) o;
        return Double.compare(response.temp, temp) == 0 && Double.compare(response.windSpeedKph, windSpeedKph) == 0 && Double.compare(response.pressureMilliBars, pressureMilliBars) == 0 && humidity == response.humidity && Objects.equals(condition, response.condition) && Objects.equals(location, response.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp, windSpeedKph, pressureMilliBars, humidity, condition, location);
    }
}
