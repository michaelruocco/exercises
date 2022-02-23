package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@Slf4j
public class Function1 implements ChannelFunction  {

    @Override
    public Arguments apply(Parameters parameters, Arguments arguments) {
        BigDecimal m = parameters.get('m');
        BigDecimal c = parameters.get('c');
        BigDecimal X = arguments.get('X');
        BigDecimal Y = m.multiply(X).add(c);
        log.info("Y=(m*X)+c --- {}=({}*{})+{}", Y, m, X, c);
        arguments.set('Y', Y);
        return arguments;
    }

}
