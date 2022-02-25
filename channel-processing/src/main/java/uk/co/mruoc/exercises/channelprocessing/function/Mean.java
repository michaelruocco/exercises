package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static uk.co.mruoc.exercises.channelprocessing.function.MathConstants.CONTEXT;

@RequiredArgsConstructor
@Slf4j
public class Mean implements ChannelFunction {

    private final char inId;
    private final char outId;

    private final AtomicInteger count;
    private final AtomicReference<BigDecimal> total;

    public Mean(char inId, char outId) {
        this(inId, outId, new AtomicInteger(), new AtomicReference<>(BigDecimal.ZERO));
    }

    @Override
    public Arguments apply(Parameters parameters, Arguments arguments) {
        BigDecimal inValue = arguments.get(inId);
        log.debug("calculating mean with {} value {}", inId, inValue);
        BigDecimal t = total.accumulateAndGet(inValue, BigDecimal::add);
        BigDecimal c = BigDecimal.valueOf(count.incrementAndGet());
        BigDecimal mean = t.divide(c, CONTEXT);
        log.debug("{}=mean({}) {}={}/{}", outId, inId, mean, t, c);
        arguments.set(outId, mean);
        return arguments;
    }

}
