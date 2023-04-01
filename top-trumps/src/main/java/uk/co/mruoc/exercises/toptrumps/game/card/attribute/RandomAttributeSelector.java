package uk.co.mruoc.exercises.toptrumps.game.card.attribute;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
public class RandomAttributeSelector implements AttributeSelector {

    @Override
    public Attribute select(Collection<Attribute> attributes) {
        List<Attribute> randomized = new ArrayList<>(attributes);
        Collections.shuffle(randomized);
        Attribute attribute = randomized.get(0);
        log.info("selected random attribute {}", attribute.getName());
        return attribute;
    }
}
