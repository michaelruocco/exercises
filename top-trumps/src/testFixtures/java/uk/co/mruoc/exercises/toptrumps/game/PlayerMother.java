package uk.co.mruoc.exercises.toptrumps.game;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerMother {

    public static Player withName(String name) {
        return new Player(name);
    }
}
