package uk.co.mruoc.exercises.channelprocessing.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.channelprocessing.input.InputParameter;
import uk.co.mruoc.exercises.channelprocessing.input.InputSelector;
import uk.co.mruoc.exercises.channelprocessing.input.InputSpec;
import uk.co.mruoc.exercises.channelprocessing.input.InputVariable;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Slf4j
public class Function1 implements ChannelFunction  {

    private final InputSpec inSpec1;
    private final InputSpec inSpec2;
    private final InputSpec inSpec3;
    private final char outId;

    public Function1() {
        this(new InputParameter('m'),
                new InputVariable('X'),
                new InputParameter('c'),
                'Y');
    }

    @Override
    public Variables apply(Parameters parameters, Variables variables) {
        BigDecimal in1 = InputSelector.select(inSpec1, parameters, variables);
        BigDecimal in2 = InputSelector.select(inSpec2, parameters, variables);
        BigDecimal in3 = InputSelector.select(inSpec3, parameters, variables);
        BigDecimal out = in1.multiply(in2).add(in3);
        log.debug("{}=({}*{})+{} {}=({}*{})+{}", outId, inSpec1.getId(), inSpec2.getId(), inSpec3.getId(), out, in1, in2, in3);
        variables.set(outId, out);
        return variables;
    }

}
