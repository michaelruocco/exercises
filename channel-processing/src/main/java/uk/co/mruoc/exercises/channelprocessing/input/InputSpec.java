package uk.co.mruoc.exercises.channelprocessing.input;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class InputSpec {

    private final InputType type;
    private final char id;

    public boolean isVariable() {
        return type == InputType.VARIABLE;
    }

    protected enum InputType {
        PARAMETER,
        VARIABLE;
    }
}
