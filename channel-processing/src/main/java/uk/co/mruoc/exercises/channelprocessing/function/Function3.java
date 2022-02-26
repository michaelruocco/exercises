package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;

import static java.math.BigDecimal.ONE;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.constant;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.variable;

@Slf4j
public class Function3 extends Divide {

    public Function3() {
        super(constant(ONE), variable('X'), 'A');
    }

}
