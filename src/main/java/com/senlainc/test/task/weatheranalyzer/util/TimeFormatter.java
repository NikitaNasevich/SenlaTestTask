package com.senlainc.test.task.weatheranalyzer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {
    public static LocalDateTime formatStringDateToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:mm");
        String hoursAndMinutes = " 00:00";
        return LocalDateTime.parse(date + hoursAndMinutes, formatter);
    }

    public static LocalDateTime formatStringWithYearFirst(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
