package uk.co.mruoc.exercises.channelprocessing.operation;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

@RequiredArgsConstructor
@Data
@Slf4j
public class Operation implements BinaryOperator<BigDecimal> {

    private final char operator;
    private final BinaryOperator<BigDecimal> binaryOperator;

    public BigDecimal apply(BigDecimal b1, BigDecimal b2) {
        BigDecimal result = binaryOperator.apply(b1, b2);
        log.debug(String.format("%s=%s%s%s", result, b1, operator, b2));
        return result;
    }

}
