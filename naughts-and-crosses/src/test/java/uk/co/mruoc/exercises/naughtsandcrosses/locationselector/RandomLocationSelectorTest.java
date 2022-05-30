package uk.co.mruoc.exercises.naughtsandcrosses.locationselector;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RandomLocationSelectorTest {

    private final Board board = mock(Board.class);

    private final LocationSelector selector = new RandomLocationSelector();

    @Test
    void shouldReturnNextFreeLocation() {
        String expectedLocation = "random-location";
        when(board.getRandomFreeLocation()).thenReturn(expectedLocation);

        String location = selector.selectLocation(board);

        assertThat(location).isEqualTo(expectedLocation);
    }

}
