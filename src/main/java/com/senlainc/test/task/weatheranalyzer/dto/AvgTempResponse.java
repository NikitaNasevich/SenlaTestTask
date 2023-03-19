package com.senlainc.test.task.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvgTempResponse {
    @JsonProperty("average_temp")
    private double averageTemp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvgTempResponse that = (AvgTempResponse) o;
        return Double.compare(that.averageTemp, averageTemp) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageTemp);
    }
}
