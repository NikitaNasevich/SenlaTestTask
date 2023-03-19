package com.senlainc.test.task.weatheranalyzer.repositories;

import com.senlainc.test.task.weatheranalyzer.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findFirstByOrderBySavedAtDesc();

    @Query(value = "SELECT AVG(current.tempC) from Weather w WHERE w.savedAt between :from and :to")
    Optional<Double> findAvgTemperature(LocalDateTime from, LocalDateTime to);
}
