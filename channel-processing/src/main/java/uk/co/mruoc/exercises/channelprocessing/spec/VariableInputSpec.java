package uk.co.mruoc.exercises.channelprocessing.spec;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.mruoc.exercises.channelprocessing.Variables;
import uk.co.mruoc.exercises.channelprocessing.parameter.Parameters;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
class VariableInputSpec implements InputSpec {

    private final char id;

    @Override
    public String getId() {
        return Character.toString(id);
    }

    @Override
    public BigDecimal select(Parameters parameters, Variables variables) {
        return variables.get(id);
    }

}
