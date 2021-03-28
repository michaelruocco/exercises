package uk.co.mruoc.exercises.circle;

import java.math.BigDecimal;

public class RadiusMustBeGreaterThanZeroException extends RuntimeException {

    public RadiusMustBeGreaterThanZeroException(BigDecimal radius) {
        super(radius.toString());
    }

}
