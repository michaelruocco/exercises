package uk.co.mruoc.exercises.cronparser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TimeUnit {

    MINUTES(0, 59),
    HOURS(0, 23),
    DAYS_OF_MONTH(1, 31),
    MONTHS(1, 12),
    DAYS_OF_WEEK(0, 6);

    private final int lowerBound;
    private final int upperBound;

    public void validate(int value) {
        if (value < lowerBound || value > upperBound) {
            throw new InvalidValueException(value, this);
        }
    }

}