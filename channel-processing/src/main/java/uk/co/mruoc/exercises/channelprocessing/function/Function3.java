package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.function.spec.FunctionSpec;

import static java.math.BigDecimal.ONE;
import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec.channel;
import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec.constant;

@Slf4j
public class Function3 extends ArithmeticFunction {

    public Function3() {
        super(new FunctionSpec("A=1/X", constant(ONE), channel('X')));
    }

}
