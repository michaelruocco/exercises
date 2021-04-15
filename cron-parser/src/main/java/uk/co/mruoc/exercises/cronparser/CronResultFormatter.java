package uk.co.mruoc.exercises.cronparser;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;

public class CronResultFormatter {

    private CronResultFormatter() {
        // utility class
    }

    public static String format(CronResult result) {
        return toLines(result).collect(Collectors.joining(lineSeparator()));
    }

    public static Stream<String> toLines(CronResult result) {
        return Stream.of(
                formatMinutes(result),
                formatHours(result),
                formatDaysOfMonth(result),
                formatMonths(result),
                formatDaysOfWeek(result),
                formatCommand(result)
        );
    }

    private static String formatMinutes(CronResult result) {
        return formatValues("minute", result::getMinutes);
    }

    private static String formatHours(CronResult result) {
        return formatValues("hour", result::getHours);
    }

    private static String formatDaysOfMonth(CronResult result) {
        return formatValues("day of month", result::getDaysOfMonth);
    }

    private static String formatMonths(CronResult result) {
        return formatValues("month", result::getMonths);
    }

    private static String formatDaysOfWeek(CronResult result) {
        return formatValues("day of week", result::getDaysOfWeek);
    }

    private static String formatCommand(CronResult result) {
        return String.format("%s %s", "command", result.getCommand());
    }

    private static String formatValues(String name, Supplier<int[]> values) {
        return String.format("%s %s", name, toString(values.get()));
    }

    private static String toString(int[] values) {
        return Arrays.stream(values)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
    }
}