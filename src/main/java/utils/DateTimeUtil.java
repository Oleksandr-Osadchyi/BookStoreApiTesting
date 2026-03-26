package utils;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DateTimeUtil {

    public static final String FULL_ZONED_UTC_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static String formatDateToString(LocalDateTime dateTime, String pattern) {
        return dateTime.format(ofPattern(pattern));
    }


}
