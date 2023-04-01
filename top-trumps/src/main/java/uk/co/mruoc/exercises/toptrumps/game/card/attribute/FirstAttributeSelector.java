package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class FirstAttributeSelector implements AttributeSelector {

    @Override
    public Attribute select(Collection<Attribute> attributes) {
        Attribute attribute = new ArrayList<>(attributes).get(0);
        log.info("selected first attribute {}", attribute.getName());
        return attribute;
    }
}
