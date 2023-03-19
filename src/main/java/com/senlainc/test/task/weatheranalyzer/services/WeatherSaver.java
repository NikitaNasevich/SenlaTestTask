package com.senlainc.test.task.weatheranalyzer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlainc.test.task.weatheranalyzer.models.Weather;
import com.senlainc.test.task.weatheranalyzer.repositories.WeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@Service
@Slf4j
public class WeatherSaver {
    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherSaver(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Scheduled(fixedRateString = "${fixed.rate.milliseconds}")
    @Transactional
    public void scheduledSaving() {
        Weather weather = new Weather();

        try {
            HttpResponse<String> response = makeRequest();
            log.info("Got response from weatherapi.com");

            weather = mapResponseToWeather(response);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while parsing response from weatherapi.com: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error occurred while getting response from weatherapi.com: " + e.getMessage());
        }

        enrichWeather(weather);
        weatherRepository.save(weather);
        log.info("Weather saved, id: " + weather.getWeatherId());
    }

    private HttpResponse<String> makeRequest() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=Minsk"))
                .header("X-RapidAPI-Key", "4d293f807fmsh938e0e17b1f8dfep1759dajsna2d3c3adf2d3")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    private Weather mapResponseToWeather(HttpResponse<String> response) throws JsonProcessingException {
        return new ObjectMapper().readValue(response.body(), Weather.class);
    }

    private void enrichWeather(Weather weather) {
        weather.setSavedAt(LocalDateTime.now());
    }
}
