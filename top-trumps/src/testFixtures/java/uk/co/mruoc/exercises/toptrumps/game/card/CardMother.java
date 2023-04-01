package uk.co.mruoc.exercises.toptrumps.game.card;

import com.neovisionaries.i18n.CountryCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.Attribute;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardMother {

    public static Card build() {
        return builder().build();
    }

    public static Card withId(long id) {
        return builder().id(id).build();
    }

    public static Collection<Card> withIds(Long... ids) {
        return withIds(List.of(ids));
    }

    public static Collection<Card> withIds(Collection<Long> ids) {
        return ids.stream()
                .map(CardMother::withId)
                .toList();
    }

    public static Card withAttribute(Attribute attribute) {
        return builder().attributes(List.of(attribute)).build();
    }

    public static Card.CardBuilder builder() {
        return Card.builder()
                .id(0)
                .fields(List.of(
                        FieldMother.name("Test Card"),
                        FieldMother.nationality(CountryCode.GB)))
                .attributes(List.of(
                        AttributeMother.shooting(50),
                        AttributeMother.dribbling(51)
                ));
    }
}
