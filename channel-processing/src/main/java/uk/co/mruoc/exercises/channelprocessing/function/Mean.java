package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static uk.co.mruoc.exercises.channelprocessing.function.MathConstants.CONTEXT;

@RequiredArgsConstructor
@Slf4j
public class Mean implements ChannelFunction {

    private final InputSpec inSpec;
    private final char resultId;

    private final AtomicReference<BigDecimal> runningSum;
    private final AtomicInteger runningCount;

    public Mean(InputSpec inSpec, char resultId) {
        this(inSpec, resultId, new AtomicReference<>(BigDecimal.ZERO), new AtomicInteger());
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal in = inSpec.select(parameters, variables);
        log.debug("calculating mean with {} value {}", inSpec.getId(), in);
        BigDecimal sum = runningSum.accumulateAndGet(in, BigDecimal::add);
        BigDecimal count = BigDecimal.valueOf(runningCount.incrementAndGet());
        BigDecimal mean = sum.divide(count, CONTEXT);
        log.debug("{}=mean({}) {}={}/{}", resultId, inSpec.getId(), mean, sum, count);
        log(mean, in, sum, count);
        variables.set(resultId, mean);
        return variables;
    }

    private void log(BigDecimal result, BigDecimal in, BigDecimal sum, BigDecimal count) {
        if (log.isDebugEnabled()) {
            log.debug("{} {}", buildAlgorithmString(), toCalculation(result, in, sum, count));
        }
    }

    private String buildAlgorithmString() {
        return String.format("%s=mean(%s)", resultId, inSpec.getId());
    }

    private String toCalculation(BigDecimal result, BigDecimal in, BigDecimal sum, BigDecimal count) {
        return String.format("%s=mean(%s) %s/%s", result, in, sum.getClass(), count);
    }

}
