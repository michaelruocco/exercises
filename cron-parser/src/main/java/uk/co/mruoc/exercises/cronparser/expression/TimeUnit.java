package uk.co.mruoc.exercises.cronparser.expression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Getter
public enum TimeUnit {

    MINUTES(0, 59),
    HOURS(0, 23),
    DAYS_OF_MONTH(1, 31),
    MONTHS(1, 12),
    DAYS_OF_WEEK(0, 6);

    private final int lowerBound;
    private final int upperBound;

    public int[] allValues() {
        return IntStream.rangeClosed(lowerBound, upperBound).toArray();
    }

    public void validate(int... values) {
        Arrays.stream(values).forEach(this::validate);
    }

    public void validate(int value) {
        if (isOutOfBounds(value)) {
            throw new NotationOutOfBoundsException(value, this);
        }
    }

    public String formattedName() {
        return name().toLowerCase().replace("_", " ");
    }

    private boolean isOutOfBounds(int value) {
        return value < lowerBound || value > upperBound;
    }

}
