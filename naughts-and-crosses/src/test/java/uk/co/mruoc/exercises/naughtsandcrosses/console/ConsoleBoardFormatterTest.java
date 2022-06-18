package uk.co.mruoc.exercises.naughtsandcrosses.console;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleBoardFormatterTest {

    private final ConsoleBoardFormatter formatter = new ConsoleBoardFormatter();

    @Test
    void shouldFormatBoardState() {
        Board board = new Board();

        String formatted = formatter.format(board);

        assertThat(formatted).isEqualTo(
                "  1 2 3\n" +
                "1 - - -\n" +
                "2 - - -\n" +
                "3 - - -");
    }

}
