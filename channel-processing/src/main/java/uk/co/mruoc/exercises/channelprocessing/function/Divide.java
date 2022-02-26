package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static uk.co.mruoc.exercises.channelprocessing.function.MathConstants.CONTEXT;

@RequiredArgsConstructor
@Slf4j
public class Divide implements ChannelFunction  {

    private final InputSpec dividendSpec;
    private final InputSpec divisorSpec;
    private final char resultId;

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal divisor = divisorSpec.select(parameters, variables);
        BigDecimal dividend = dividendSpec.select(parameters, variables);
        BigDecimal result = dividend.divide(divisor, CONTEXT);
        variables.set(resultId, result);
        log(result, dividend, divisor);
        return variables;
    }

    private void log(BigDecimal result, BigDecimal dividend, BigDecimal divisor) {
        if (log.isDebugEnabled()) {
            log.debug("{} {}", buildAlgorithmString(), toCalculation(result, dividend, divisor));
        }
    }

    private String buildAlgorithmString() {
        return String.format("%s=%s/%s", resultId, dividendSpec.getId(), divisorSpec.getId());
    }

    private static String toCalculation(BigDecimal result, BigDecimal dividend, BigDecimal divisor) {
        return String.format("%s=%s/%s", result, dividend, divisor);
    }

}
