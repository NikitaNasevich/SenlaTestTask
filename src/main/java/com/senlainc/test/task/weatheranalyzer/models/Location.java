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
@Entity(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private int locationId;

    @Column(name = "name")
    private String name;

    @Column(name = "region")
    private String region;

    @Column(name = "country")
    private String country;

    @Column(name = "latitude")
    @JsonProperty("lat")
    private double latitude;

    @Column(name = "longitude")
    @JsonProperty("lon")
    private double longitude;

    @Column(name = "time_zone")
    @JsonProperty("tz_id")
    private String timeZone;

    @Column(name = "unix_local_date_time")
    @JsonProperty("localtime_epoch")
    private long unixLocalDateTime;

    @Column(name = "local_date_time")
    @JsonProperty("localtime")
    private LocalDateTime localDateTime;

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = TimeFormatter.formatStringWithYearFirst(localDateTime);
    }
}
