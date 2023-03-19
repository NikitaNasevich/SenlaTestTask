package com.senlainc.test.task.weatheranalyzer.util;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeFormatterTest {

    @Test
    void formatStringDateToLocalDateTimeTest() {
        LocalDateTime expectedResult = LocalDateTime.of(2023, 3, 16, 0, 0);
        LocalDateTime actualResult = TimeFormatter.formatStringDateToLocalDateTime("16-03-2023");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void formatStringWithYearFirstTest() {
        LocalDateTime expectedResult = LocalDateTime.of(2023, 3, 16, 9, 35);
        LocalDateTime actualResult = TimeFormatter.formatStringWithYearFirst("2023-03-16 09:35");

        assertEquals(expectedResult, actualResult);
    }
}
