package uk.co.mruoc.exercises.toptrumps.game.card;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.Attribute;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.NumericHighestWinsAttribute;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AttributeMother {

    public static Attribute shooting(double value) {
        return new NumericHighestWinsAttribute("shooting", value);
    }

    public static Attribute dribbling(double value) {
        return new NumericHighestWinsAttribute("dribbling", value);
    }
}
