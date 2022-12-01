package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.mruoc.exercises.toptrumps.game.card.Result;

import static uk.co.mruoc.exercises.toptrumps.game.card.Result.DRAW;
import static uk.co.mruoc.exercises.toptrumps.game.card.Result.LOSE;
import static uk.co.mruoc.exercises.toptrumps.game.card.Result.WIN;

@Slf4j
@RequiredArgsConstructor
@Data
public class NumericHighestWinsAttribute implements Attribute {

    private final String name;
    private final Double value;

    @Override
    public Result calculateResult(Attribute otherAttribute) {
        Result result = doCalculateResult(otherAttribute);
        log.info("calculated result {} for attribute {} with value {} against other value {}",
                result,
                name,
                value,
                otherAttribute.getValue());
        return result;
    }

    private Result doCalculateResult(Attribute otherAttribute) {
        Double otherValue = otherAttribute.getValueAs(Double.class);
        if (value > otherValue) {
            return WIN;
        }
        if (value < otherValue) {
            return LOSE;
        }
        return DRAW;
    }
}
