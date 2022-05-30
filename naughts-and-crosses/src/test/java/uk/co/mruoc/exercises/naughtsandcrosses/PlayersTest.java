package uk.co.mruoc.exercises.naughtsandcrosses;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    private static final String CROSS = "X";
    private static final String NAUGHT = "O";

    private final Players players = new Players();

    @Test
    void shouldDefaultFirstPlayerAsCrosses() {
        String currentPlayer = players.getCurrentPlayer();

        assertThat(currentPlayer).isEqualTo(CROSS);
    }

    @Test
    void shouldSwitchPlayerToNaughts() {
        players.switchPlayer();

        String currentPlayer = players.getCurrentPlayer();

        assertThat(currentPlayer).isEqualTo(NAUGHT);
    }

    @Test
    void shouldSwitchPlayerToBackToCrosses() {
        players.switchPlayer();
        players.switchPlayer();

        String currentPlayer = players.getCurrentPlayer();

        assertThat(currentPlayer).isEqualTo(CROSS);
    }

    @Test
    void shouldSwitchPlayerToBackToNaughts() {
        players.switchPlayer();
        players.switchPlayer();
        players.switchPlayer();

        String currentPlayer = players.getCurrentPlayer();

        assertThat(currentPlayer).isEqualTo(NAUGHT);
    }
}
