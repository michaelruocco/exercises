package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

public class AttributeNotFoundException extends RuntimeException {

    public AttributeNotFoundException(String name) {
        super(name);
    }
}
