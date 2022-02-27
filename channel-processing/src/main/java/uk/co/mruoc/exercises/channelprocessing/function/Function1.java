package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.function.spec.FunctionSpec;

import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpecFactory.channel;
import static uk.co.mruoc.exercises.channelprocessing.function.spec.InputSpecFactory.parameter;

@Slf4j
public class Function1 extends ArithmeticFunction {

    public Function1() {
        super(new FunctionSpec("Y=m*X+c", parameter('m'), channel('X'), parameter('c')));
    }

}
