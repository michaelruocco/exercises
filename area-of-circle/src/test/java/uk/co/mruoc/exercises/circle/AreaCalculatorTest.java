package uk.co.mruoc.exercises.circle;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class AreaCalculatorTest {

    private final AreaCalculator calculator = new AreaCalculator();

    @Test
    void shouldReturnErrorIfRadiusIsZero() {
        BigDecimal radius = BigDecimal.ZERO;

        Throwable error = catchThrowable(() -> calculator.calculateArea(radius));

        assertThat(error)
                .isInstanceOf(RadiusMustBeGreaterThanZeroException.class)
                .hasMessage(radius.toString());
    }

    @Test
    void shouldReturnErrorIfRadiusIsNegative() {
        BigDecimal radius = BigDecimal.valueOf(-0.001);

        Throwable error = catchThrowable(() -> calculator.calculateArea(radius));

        assertThat(error)
                .isInstanceOf(RadiusMustBeGreaterThanZeroException.class)
                .hasMessage(radius.toString());
    }

    @Test
    void shouldCalculateAreaGivenSmallRadius() {
        BigDecimal radius = BigDecimal.valueOf(0.001);

        BigDecimal area = calculator.calculateArea(radius);

        assertThat(area).isEqualTo(new BigDecimal("0.000003141592653589793"));
    }

    @Test
    void shouldCalculateAreaGivenRadius() {
        BigDecimal radius = BigDecimal.TEN;

        BigDecimal area = calculator.calculateArea(radius);

        assertThat(area).isEqualTo(new BigDecimal("314.159265358979300"));
    }

}
