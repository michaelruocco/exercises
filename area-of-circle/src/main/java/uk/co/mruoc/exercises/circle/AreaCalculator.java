package uk.co.mruoc.exercises.circle;

import java.math.BigDecimal;

public class AreaCalculator {

    private static final BigDecimal PI = BigDecimal.valueOf(Math.PI);

    public BigDecimal calculateArea(BigDecimal radius) {
        if (isLessThanOrEqualToZero(radius)) {
            throw new RadiusMustBeGreaterThanZeroException(radius);
        }
        return radius.multiply(radius).multiply(PI);
    }

    private boolean isLessThanOrEqualToZero(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) <= 0;
    }

}
