package uk.co.mruoc.exercises.cronparser.expression;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CronResult {

    private final int[] minutes;
    private final int[] hours;
    private final int[] daysOfMonth;
    private final int[] months;
    private final int[] daysOfWeek;
    private final String command;

    public int[] getMinutes() {
        return minutes;
    }

    public int[] getHours() {
        return hours;
    }

    public int[] getDaysOfMonth() {
        return daysOfMonth;
    }

    public int[] getMonths() {
        return months;
    }

    public int[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public String getCommand() {
        return command;
    }

}
