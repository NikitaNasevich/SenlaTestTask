package com.senlainc.test.task.weatheranalyzer.dto;

import com.senlainc.test.task.weatheranalyzer.util.TimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvgTempRequest {
    private LocalDateTime from;
    private LocalDateTime to;

    public void setFrom(String from) {
        this.from = TimeFormatter.formatStringDateToLocalDateTime(from);
    }

    public void setTo(String to) {
        this.to = TimeFormatter.formatStringDateToLocalDateTime(to);
    }
}
