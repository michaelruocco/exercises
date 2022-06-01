package uk.co.mruoc.exercises.naughtsandcrosses;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.content.ContentLoader.loadContentFromClasspath;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

class NaughtsAndCrossesTest {

    @Test
    void shouldPlayFixedConsoleGame() throws Exception {
        String output = tapSystemOut(() -> {
            NaughtsAndCrosses.main(new String[]{"FIXED"});
        });

        assertThat(output).isEqualTo(loadContentFromClasspath("expected-fixed-console-game-output.txt"));
    }

    @Test
    void shouldPlayDrawConsoleGame() throws Exception {
        String output = tapSystemOut(() -> {
            NaughtsAndCrosses.main(new String[]{"DRAW"});
        });

        assertThat(output).isEqualTo(loadContentFromClasspath("expected-draw-console-game-output.txt"));
    }
}
