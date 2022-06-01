package uk.co.mruoc.exercises.naughtsandcrosses;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.file.content.ContentLoader.loadContentFromClasspath;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemErrAndOut;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;
import static uk.org.webcompere.systemstubs.SystemStubs.withTextFromSystemIn;

class NaughtsAndCrossesTest {

    @Test
    void shouldPlayFixedConsoleGame() throws Exception {
        String output = tapSystemOut(() -> NaughtsAndCrosses.main(new String[]{"FIXED"}));

        assertThat(output).isEqualTo(loadContentFromClasspath("expected-fixed-console-game-output.txt"));
    }

    @Test
    void shouldPlayDrawConsoleGame() throws Exception {
        String output = tapSystemOut(() -> NaughtsAndCrosses.main(new String[]{"DRAW"}));

        assertThat(output).isEqualTo(loadContentFromClasspath("expected-draw-console-game-output.txt"));
    }

    @Test
    void shouldPlayInteractiveConsoleGame() throws Exception {
        withTextFromSystemIn("4-4", "1-1", "1-1", "1-2", "2-1", "2-2", "3-1")
                .execute(() -> {
                    String output = tapSystemErrAndOut(() -> NaughtsAndCrosses.main(new String[]{"INTERACTIVE"}));
                    assertThat(output).isEqualTo(loadContentFromClasspath("expected-interactive-console-game-output.txt"));
                });
    }
}
