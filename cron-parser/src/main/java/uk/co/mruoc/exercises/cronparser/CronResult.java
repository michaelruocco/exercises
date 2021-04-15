package uk.co.mruoc.exercises.cronparser;

import lombok.Builder;

@Builder
public class CronResult {

    private final int[] hours;
    private final int[] daysOfMonth;

    public int[] getMinutes() {
        return new int[]{-1};
    }

    public int[] getHours() {
        return hours;
    }

    public int[] getDaysOfMonth() {
        return daysOfMonth;
    }

    public int[] getMonths() {
        return new int[]{-1};
    }

    public int[] getDayOfWeek() {
        return new int[]{-1};
    }

    public String getCommand() {
        return "";
    }

}
