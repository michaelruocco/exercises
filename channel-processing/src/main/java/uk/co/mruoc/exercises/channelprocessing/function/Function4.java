package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.function.spec.FunctionSpec;

import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpecFactory.channel;
import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpecFactory.metric;

@Slf4j
public class Function4 extends ArithmeticFunction {

    public Function4() {
        super(new FunctionSpec("C=X+b", channel('X'), metric('b')));
    }

}
