package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.argument.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static uk.co.mruoc.exercises.channelprocessing.function.MathConstants.CONTEXT;

@RequiredArgsConstructor
@Slf4j
public class Function2 implements ChannelFunction {

    private final AtomicInteger count;
    private final AtomicReference<BigDecimal> total;

    public Function2() {
        this(new AtomicInteger(), new AtomicReference<>(BigDecimal.ZERO));
    }

    @Override
    public Arguments apply(Parameters parameters, Arguments arguments) {
        BigDecimal A = arguments.get('A');
        BigDecimal Y = arguments.get('Y');
        BigDecimal B = A.add(Y);
        log.info("B=A+Y --- {}={}+{}", B, A, Y);
        BigDecimal b = toMean(B);
        arguments.set('b', b);
        return arguments;
    }

    private BigDecimal toMean(BigDecimal B) {
        BigDecimal t = total.accumulateAndGet(B, BigDecimal::add);
        BigDecimal c = BigDecimal.valueOf(count.incrementAndGet());
        BigDecimal mean = t.divide(c, CONTEXT);
        log.info("b=mean(B) --- {}={}/{}", mean, t, c);
        return mean;
    }

}