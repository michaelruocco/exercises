package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

import java.util.Collection;

public interface AttributeSelector {

    Attribute select(Collection<Attribute> attributes);
}
