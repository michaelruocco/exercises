package uk.co.mruoc.exercises.channelprocessing.function.spec;

import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

public interface InputSpec {

    String getId();

    BigDecimal select(Parameters parameters, Variables variables);

}
