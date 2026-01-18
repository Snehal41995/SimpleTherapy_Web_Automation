package com.simpleTherapy.web.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    // Converts UI date → dd/MM/yy
    public static String normalizeDate(String uiDate) {

        // UI format: 01-01-1995
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Target format (Excel expectation)
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("d/MM/yy");

        LocalDate date = LocalDate.parse(uiDate, uiFormatter);
        return date.format(targetFormatter);
    }
}
