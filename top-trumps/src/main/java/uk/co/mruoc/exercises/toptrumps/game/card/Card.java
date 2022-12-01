package uk.co.mruoc.exercises.toptrumps.game.card;

import lombok.Builder;
import lombok.Data;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.Attribute;
import uk.co.mruoc.exercises.toptrumps.game.card.attribute.AttributeNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Data
public class Card {

    private final long id;
    private final Collection<Field> fields;
    private final Collection<Attribute> attributes;

    public Collection<String> getAttributeNames() {
        return attributes.stream().map(Attribute::getName).collect(Collectors.toSet());
    }

    public Result calculateResult(Card otherCard, String attributeName) {
        return calculateResult(otherCard.findAttributeByName(attributeName));
    }

    public Result calculateResult(Attribute otherAttribute) {
        Attribute attribute = findAttributeByName(otherAttribute.getName());
        return attribute.calculateResult(otherAttribute);
    }

    public Attribute findAttributeByName(String name) {
        return attributes.stream()
                .filter(a -> a.hasName(name))
                .findFirst()
                .orElseThrow(() -> new AttributeNotFoundException(name));
    }

    public static String toFormattedCardIds(Collection<Card> cards) {
        return String.join(",", toCardIdsAsStrings(cards));
    }

    private static Collection<String> toCardIdsAsStrings(Collection<Card> cards) {
        return cards.stream().map(Card::getId).map(id -> Long.toString(id)).toList();
    }
}
