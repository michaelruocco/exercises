package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Slf4j
public class Add implements ChannelFunction {

    private final InputSpec inSpec1;
    private final InputSpec inSpec2;
    private final char resultId;

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal in1 = inSpec1.select(parameters, variables);
        BigDecimal in2 = inSpec2.select(parameters, variables);
        BigDecimal result = in1.add(in2);
        variables.set(resultId, result);
        log(result, in1, in2);
        return variables;
    }

    private void log(BigDecimal result, BigDecimal dividend, BigDecimal divisor) {
        if (log.isDebugEnabled()) {
            log.debug("{} - {}", buildAlgorithmString(), toCalculation(result, dividend, divisor));
        }
    }

    private String buildAlgorithmString() {
        return String.format("%s=%s+%s", resultId, inSpec1.getId(), inSpec2.getId());
    }

    private static String toCalculation(BigDecimal result, BigDecimal in1, BigDecimal in2) {
        return String.format("%s=%s+%s", result, in1, in2);
    }

}
