package com.senlainc.test.task.weatheranalyzer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.senlainc.test.task.weatheranalyzer.util.TimeFormatter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Current")
public class Current {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "current_id")
    private int currentId;

    @Column(name = "unix_last_update_date_time")
    @JsonProperty("last_updated_epoch")
    private long unixLastUpdatesDateTime;

    @Column(name = "last_update_date_time")
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdatedDateTime;
    @Column(name = "temp_c")
    @JsonProperty("temp_c")
    private double tempC;

    @Column(name = "temp_f")
    @JsonProperty("temp_f")
    private double tempF;

    @Column(name = "is_day")
    @JsonProperty("is_day")
    private boolean isDay;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "condition_id", referencedColumnName = "condition_id")
    private Condition condition;

    @Column(name = "wind_speed_mph")
    @JsonProperty("wind_mph")
    private double windSpeedMph;

    @Column(name = "wind_speed_kph")
    @JsonProperty("wind_kph")
    private double windSpeedKph;

    @Column(name = "wind_direction_degree")
    @JsonProperty("wind_degree")
    private int windDirectionDegree;

    @Column(name = "wind_direction_compass")
    @JsonProperty("wind_dir")
    private String windDirectionCompass;

    @Column(name = "pressure_mb")
    @JsonProperty("pressure_mb")
    private double pressureMillibars;

    @Column(name = "pressure_inch")
    @JsonProperty("pressure_in")
    private double pressureInch;

    @Column(name = "precipitation_mm")
    @JsonProperty("precip_mm")
    private double precipitationMm;

    @Column(name = "precipitation_inch")
    @JsonProperty("precip_in")
    private double precipitationInch;

    @Column(name = "humidity")
    private int humidity;

    @Column(name = "cloud_cover_percent")
    @JsonProperty("cloud")
    private int cloudCoverPercent;

    @Column(name = "feels_like_temp_c")
    @JsonProperty("feelslike_c")
    private double feelsLikeTempC;

    @Column(name = "feels_like_temp_f")
    @JsonProperty("feelslike_f")
    private double feelsLikeTempF;

    @Column(name = "visibility_km")
    @JsonProperty("vis_km")
    private double visibilityKm;

    @Column(name = "visibility_miles")
    @JsonProperty("vis_miles")
    private double visibilityMiles;

    @Column(name = "uv_index")
    @JsonProperty("uv")
    private double uvIndex;

    @Column(name = "wind_gust_mph")
    @JsonProperty("gust_mph")
    private double windGustMph;

    @Column(name = "wind_gust_kph")
    @JsonProperty("gust_kph")
    private double windGustKph;

    public void setLastUpdatedDateTime(String lastUpdatedDateTime) {
        this.lastUpdatedDateTime = TimeFormatter.formatStringWithYearFirst(lastUpdatedDateTime);
    }
}
