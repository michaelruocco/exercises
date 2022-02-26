package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.parameter;
import static uk.co.mruoc.exercises.channelprocessing.input.InputSpec.variable;

@RequiredArgsConstructor
@Slf4j
public class Function1 implements ChannelFunction  {

    private final InputSpec inSpec1;
    private final InputSpec inSpec2;
    private final InputSpec inSpec3;
    private final char outId;

    public Function1() {
        this(parameter('m'), variable('X'), parameter('c'), 'Y');
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal in1 = inSpec1.select(parameters, variables);
        BigDecimal in2 = inSpec2.select(parameters, variables);
        BigDecimal in3 = inSpec3.select(parameters, variables);
        BigDecimal out = in1.multiply(in2).add(in3);
        //TODO compose this function into new multiply function and add function
        // need to split out functions that just operator on BigDecimal inputs and outputs and then
        // have a class to select the numeric function based on operator
        log.debug("{}=({}*{})+{} {}=({}*{})+{}", outId, inSpec1.getId(), inSpec2.getId(), inSpec3.getId(), out, in1, in2, in3);
        variables.set(outId, out);
        return variables;
    }

}
