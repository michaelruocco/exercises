package uk.co.mruoc.exercises.naughtsandcrosses;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.naughtsandcrosses.console.DrawConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.FixedConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.InteractiveConsoleGame;
import uk.co.mruoc.exercises.naughtsandcrosses.console.RandomConsoleGame;

import static org.assertj.core.api.Assertions.assertThat;

class GameFactoryTest {

    private final GameFactory factory = new GameFactory();

    @Test
    void shouldCreateInteractiveGameIfTypeIsInteractive() {
        String type = "interactive";

        Game game = factory.build(type);

        assertThat(game).isInstanceOf(InteractiveConsoleGame.class);
    }

    @Test
    void shouldCreateFixedGameIfTypeIsFixed() {
        String type = "fixed";

        Game game = factory.build(type);

        assertThat(game).isInstanceOf(FixedConsoleGame.class);
    }

    @Test
    void shouldCreateDrawGameIfTypeIsDraw() {
        String type = "draw";

        Game game = factory.build(type);

        assertThat(game).isInstanceOf(DrawConsoleGame.class);
    }

    @Test
    void shouldCreateRandomGameForAnyOtherType() {
        String type = "other";

        Game game = factory.build(type);

        assertThat(game).isInstanceOf(RandomConsoleGame.class);
    }

}
