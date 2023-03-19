package com.senlainc.test.task.weatheranalyzer.services;

import com.senlainc.test.task.weatheranalyzer.dto.AvgTempRequest;
import com.senlainc.test.task.weatheranalyzer.dto.AvgTempResponse;
import com.senlainc.test.task.weatheranalyzer.dto.WeatherResponse;
import com.senlainc.test.task.weatheranalyzer.exceptions.WeatherForPeriodNotFoundException;
import com.senlainc.test.task.weatheranalyzer.exceptions.WeatherNotFoundException;
import com.senlainc.test.task.weatheranalyzer.models.Condition;
import com.senlainc.test.task.weatheranalyzer.models.Current;
import com.senlainc.test.task.weatheranalyzer.models.Location;
import com.senlainc.test.task.weatheranalyzer.models.Weather;
import com.senlainc.test.task.weatheranalyzer.repositories.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;


    @Test
    void findLastSavedWeatherTest() {
        Weather weather = initWeather();
        WeatherResponse expectedResult = initWeatherResponse();

        doReturn(Optional.of(weather)).when(weatherRepository).findFirstByOrderBySavedAtDesc();

        WeatherResponse actualResult = weatherService.findLastSavedWeather();

        assertEquals(expectedResult, actualResult);

        verify(weatherRepository).findFirstByOrderBySavedAtDesc();
        verifyNoMoreInteractions(weatherRepository);
    }

    @Test
    void whenFindLastSavedWeather_thenException() {
        assertThrows(WeatherNotFoundException.class, () -> weatherService.findLastSavedWeather());
    }


    @Test
    void findAvgTempForPeriodTest() {
        AvgTempRequest request = new AvgTempRequest(LocalDateTime.of(2023, 3, 16, 0, 0),
                LocalDateTime.of(2023, 3, 17, 0, 0));

        AvgTempResponse expectedResult = new AvgTempResponse(3.65);

        doReturn(Optional.of(3.65)).when(weatherRepository).findAvgTemperature(request.getFrom(), request.getTo());

        AvgTempResponse actualResult = weatherService.findAvgTempForPeriod(request);

        assertEquals(actualResult, expectedResult);

        verify(weatherRepository).findAvgTemperature(request.getFrom(), request.getTo());
        verifyNoMoreInteractions(weatherRepository);
    }

    @Test
    void whenFindAvgTempForPeriod_thenException() {
        AvgTempRequest request = new AvgTempRequest(LocalDateTime.of(2023, 3, 16, 0, 0),
                LocalDateTime.of(2023, 3, 30, 0, 0));
        assertThrows(WeatherForPeriodNotFoundException.class, () -> weatherService.findAvgTempForPeriod(request));
    }

    public Weather initWeather() {
        return Weather.builder()
                .weatherId(55)
                .location(
                        Location.builder()
                                .locationId(55)
                                .name("Minsk")
                                .region("Minsk")
                                .country("Belarus")
                                .latitude(53.9)
                                .longitude(27.57)
                                .timeZone("Europe/Minsk")
                                .unixLocalDateTime(1678860839)
                                .localDateTime(LocalDateTime.of(2023, 3, 15, 9, 13))
                                .build()
                )
                .current(
                        Current.builder()
                                .currentId(55)
                                .unixLastUpdatesDateTime(1678860000)
                                .lastUpdatedDateTime(LocalDateTime.of(2023, 3, 15, 9, 0))
                                .tempC(4.0)
                                .tempF(37.4)
                                .isDay(true)
                                .condition(
                                        Condition.builder()
                                                .conditionId(55)
                                                .text("Light rain")
                                                .iconLink("//cdn.weatherapi.com/weather/64x64/day/296.png")
                                                .code(1183)
                                                .build()
                                )
                                .windSpeedMph(9.4)
                                .windSpeedKph(15.1)
                                .windDirectionDegree(150)
                                .windDirectionCompass("SSE")
                                .pressureMillibars(1001.0)
                                .pressureInch(29.56)
                                .precipitationMm(0.0)
                                .precipitationInch(0.0)
                                .humidity(87)
                                .cloudCoverPercent(62)
                                .feelsLikeTempC(-1.0)
                                .feelsLikeTempF(30.3)
                                .visibilityKm(10.0)
                                .visibilityMiles(6.0)
                                .uvIndex(2.0)
                                .windGustKph(25.6)
                                .windGustMph(15.9)
                                .build()
                )
                .savedAt(LocalDateTime.of(2023, 3, 16, 12, 00))
                .build();
    }

    public WeatherResponse initWeatherResponse() {
        return WeatherResponse.builder()
                .temp(4.0)
                .windSpeedKph(15.1)
                .pressureMilliBars(1001.0)
                .humidity(87)
                .condition("Light rain")
                .location("Minsk")
                .build();
    }
}
