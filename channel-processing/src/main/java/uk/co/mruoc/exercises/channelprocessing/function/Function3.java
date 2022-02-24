package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static uk.co.mruoc.exercises.channelprocessing.function.MathConstants.CONTEXT;

@Slf4j
public class Function3 implements ChannelFunction  {

    @Override
    public Arguments apply(Parameters parameters, Arguments arguments) {
        BigDecimal X = arguments.get('X');
        BigDecimal A = ONE.divide(X, CONTEXT);
        arguments.set('A', A);
        log.debug("A=1/X --- {}=1/{}", A, X);
        return arguments;
    }

}
