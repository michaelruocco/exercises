package uk.co.mruoc.exercises.naughtsandcrosses.locationselector;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NextFreeLocationSelectorTest {

    private final Board board = mock(Board.class);

    private final LocationSelector selector = new NextFreeLocationSelector();

    @Test
    void shouldReturnNextFreeLocation() {
        String expectedLocation = "next-free-location";
        when(board.getNextFreeLocation()).thenReturn(expectedLocation);

        String location = selector.selectLocation(board);

        assertThat(location).isEqualTo(expectedLocation);
    }

}
