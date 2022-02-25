package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@Slf4j
public class Function1 implements ChannelFunction  {

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal m = parameters.get('m');
        BigDecimal c = parameters.get('c');
        BigDecimal X = variables.get('X');
        BigDecimal Y = m.multiply(X).add(c);
        log.debug("Y=(m*X)+c {}=({}*{})+{}", Y, m, X, c);
        variables.set('Y', Y);
        return variables;
    }

}
