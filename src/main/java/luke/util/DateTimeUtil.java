package luke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses and formats dates and times used by Luke.
 */
public final class DateTimeUtil {
    private static final DateTimeFormatter INPUT_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd uuuu");
    private static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd uuuu HH:mm");

    private DateTimeUtil() {
    }

    /**
     * Parses a date in {@code yyyy-MM-dd} format.
     *
     * @param input Date text to parse.
     * @return Parsed date.
     */
    public static LocalDate parseDate(String input) {
        return LocalDate.parse(input, INPUT_DATE_FORMATTER);
    }

    /**
     * Parses a date and time in {@code yyyy-MM-dd HH:mm} format.
     *
     * @param input Date-time text to parse.
     * @return Parsed date and time.
     */
    public static LocalDateTime parseDateTime(String input) {
        return LocalDateTime.parse(input, INPUT_DATE_TIME_FORMATTER);
    }

    /**
     * Formats a date for display to the user.
     *
     * @param date Date to format.
     * @return User-friendly date text.
     */
    public static String formatDate(LocalDate date) {
        return date.format(DISPLAY_DATE_FORMATTER);
    }

    /**
     * Formats a date and time for display to the user.
     *
     * @param dateTime Date and time to format.
     * @return User-friendly date-time text.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_DATE_TIME_FORMATTER);
    }
}
