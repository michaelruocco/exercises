package uk.co.mruoc.exercises.naughtsandcrosses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
public class Players {

    private static final String PLAYER_1 = "X";
    private static final String PLAYER_2 = "O";

    @Getter
    private String currentPlayer;

    public Players() {
        this(PLAYER_1);
    }

    public Stream<String> stream() {
        return Stream.of(PLAYER_1, PLAYER_2);
    }

    public void switchPlayer() {
        if (currentPlayer.equals(PLAYER_1)) {
            currentPlayer = PLAYER_2;
            return;
        }
        currentPlayer = PLAYER_1;
    }

}
