package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.ChannelFunction;
import uk.co.mruoc.exercises.channelprocessing.argument.Arguments;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@Slf4j
public class Function4 implements ChannelFunction  {

    @Override
    public Arguments apply(Parameters parameters, Arguments arguments) {
        BigDecimal X = arguments.get('X');
        BigDecimal b = arguments.get('b');
        BigDecimal C = X.add(b);
        arguments.set('C', C);
        log.info("C=X+b --- {}={}+{}", C, X, b);
        return arguments;
    }

}
