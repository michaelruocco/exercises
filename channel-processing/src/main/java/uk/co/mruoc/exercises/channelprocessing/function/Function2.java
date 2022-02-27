package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.function.spec.FunctionSpec;

import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpec.channel;

@Slf4j
public class Function2 extends CompositeFunction {

    public Function2() {
        super(new ArithmeticFunction(new FunctionSpec("B=A+Y", channel('A'), channel('Y'))),
                new MeanFunction(channel('B'), 'b'));
    }

}
