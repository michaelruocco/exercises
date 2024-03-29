package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Slf4j
public class MeanFunction implements ChannelFunction {

    private final InputSpec inSpec;
    private final char target;

    private final AtomicReference<BigDecimal> runningSum;
    private final AtomicInteger runningCount;

    public MeanFunction(InputSpec inSpec, char target) {
        this(inSpec, target, new AtomicReference<>(BigDecimal.ZERO), new AtomicInteger());
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal in = inSpec.select(parameters, variables);
        logExpression(in);
        BigDecimal sum = runningSum.accumulateAndGet(in, BigDecimal::add);
        int count = runningCount.incrementAndGet();
        BigDecimal mean = Operations.divide(sum, BigDecimal.valueOf(count));
        return variables.set(target, mean);
    }

    private void logExpression(BigDecimal in) {
        log.debug("{}=mean({}) {}={}", target, inSpec.getId(), inSpec.getId(), in);
    }

}
