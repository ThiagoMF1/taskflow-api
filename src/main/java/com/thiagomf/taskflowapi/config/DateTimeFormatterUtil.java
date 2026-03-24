package com.thiagomf.taskflowapi.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("'Date:' yyyy-MM-dd ' / Time:' HH:mm:ss");

    private DateTimeFormatterUtil() {
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}