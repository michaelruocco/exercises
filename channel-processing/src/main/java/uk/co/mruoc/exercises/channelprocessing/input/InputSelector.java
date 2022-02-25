package uk.co.mruoc.exercises.channelprocessing.input;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputSelector {

    public static BigDecimal select(InputSpec spec, Parameters parameters, Variables variables) {
        if (spec.isVariable()) {
            return variables.get(spec.getId());
        }
        return parameters.get(spec.getId());
    }

}
