package com.senlainc.test.task.weatheranalyzer.services;

import com.senlainc.test.task.weatheranalyzer.dto.AvgTempRequest;
import com.senlainc.test.task.weatheranalyzer.dto.AvgTempResponse;
import com.senlainc.test.task.weatheranalyzer.dto.WeatherResponse;
import com.senlainc.test.task.weatheranalyzer.exceptions.WeatherForPeriodNotFoundException;
import com.senlainc.test.task.weatheranalyzer.exceptions.WeatherNotFoundException;
import com.senlainc.test.task.weatheranalyzer.models.Weather;
import com.senlainc.test.task.weatheranalyzer.repositories.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class WeatherService {
    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public WeatherResponse findLastSavedWeather() {
        WeatherResponse response = weatherRepository.findFirstByOrderBySavedAtDesc()
                .map(this::convertToWeatherResponse).orElseThrow(() -> new WeatherNotFoundException("Weather not found"));

        log.info("Sent last saved weather response;");
        return response;
    }

    public AvgTempResponse findAvgTempForPeriod(AvgTempRequest weatherRequest) {
        Double avgTemp = weatherRepository.findAvgTemperature(
                weatherRequest.getFrom(),
                weatherRequest.getTo()).orElseThrow(() -> new WeatherForPeriodNotFoundException("Incorrect period"));

        log.info("sent average temperature for period: " + weatherRequest.getFrom() + ", " + weatherRequest.getTo() + ";");
        return convertToAvgTempResponse(avgTemp);
    }

    private WeatherResponse convertToWeatherResponse(Weather weather) {
        WeatherResponse response = new WeatherResponse();
        response.setTemp(weather.getCurrent().getTempC());
        response.setWindSpeedKph(weather.getCurrent().getWindSpeedKph());
        response.setPressureMilliBars(weather.getCurrent().getPressureMillibars());
        response.setHumidity(weather.getCurrent().getHumidity());
        response.setCondition(weather.getCurrent().getCondition().getText());
        response.setLocation(weather.getLocation().getName());
        return response;
    }

    private AvgTempResponse convertToAvgTempResponse(Double avgTemp) {
        AvgTempResponse avgTempResponse = new AvgTempResponse();
        avgTempResponse.setAverageTemp(avgTemp);
        return avgTempResponse;
    }

}
