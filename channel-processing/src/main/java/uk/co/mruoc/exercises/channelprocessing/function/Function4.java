package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.spec.FunctionSpec;

import static uk.co.mruoc.exercises.channelprocessing.spec.InputSpec.variable;

@Slf4j
public class Function4 extends ArithmeticFunction {

    public Function4() {
        super(new FunctionSpec("C=X+b", variable('X'), variable('b')));
    }

}
