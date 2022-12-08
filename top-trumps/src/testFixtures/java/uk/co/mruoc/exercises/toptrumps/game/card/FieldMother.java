package uk.co.mruoc.exercises.toptrumps.game.card;

import com.neovisionaries.i18n.CountryCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldMother {

    public static Field name(String value) {
        return new Field("name", value);
    }

    public static Field nationality(CountryCode value) {
        return new Field("nationality", value.toString());
    }
}
