package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.RoundingMode.HALF_EVEN;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class Operations {

    private static final MathContext CONTEXT = new MathContext(21, HALF_EVEN);

    public static BigDecimal subtract(BigDecimal b1, BigDecimal b2) {
        BigDecimal result =  b1.subtract(b2, CONTEXT);
        log.debug("{}={}-{}", result, b1, b2);
        return result;
    }

    public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
        BigDecimal result =  b1.add(b2, CONTEXT);
        log.debug("{}={}+{}", result, b1, b2);
        return result;
    }

    public static BigDecimal multiply(BigDecimal b1, BigDecimal b2) {
        BigDecimal result =  b1.multiply(b2, CONTEXT);
        log.debug("{}={}*{}", result, b1, b2);
        return result;
    }

    public static BigDecimal divide(BigDecimal b1, BigDecimal b2) {
        BigDecimal result =  b1.divide(b2, CONTEXT);
        log.debug("{}={}/{}", result, b1, b2);
        return result;
    }
}
