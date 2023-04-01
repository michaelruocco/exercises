package uk.co.mruoc.exercises.toptrumps.game.card;

import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

@RequiredArgsConstructor
public class RandomShuffler implements Shuffler {

    private final Random random;

    public RandomShuffler() {
        this(new SecureRandom());
    }

    @Override
    public int compare(Card c1, Card c2) {
        if (random.nextBoolean()) {
            return 1;
        }
        return 0;
    }
}
