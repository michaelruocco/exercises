package uk.co.mruoc.exercises.naughtsandcrosses.locationselector;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.naughtsandcrosses.board.Board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

class SpecificLocationSelectorTest {

    private static final String KEY_1 = "key-1";
    private static final String KEY_2 = "key-2";

    private final Board board = mock(Board.class);

    private final LocationSelector selector = new SpecificLocationSelector(KEY_1, KEY_2);

    @Test
    void shouldSelectLocationsUsingSpecificKeys() {
        String location1 = selector.selectLocation(board);
        String location2 = selector.selectLocation(board);

        assertThat(location1).isEqualTo(KEY_1);
        assertThat(location2).isEqualTo(KEY_2);
    }

    @Test
    void shouldThrowExceptionWhenAllKeysSelected() {
        selector.selectLocation(board);
        selector.selectLocation(board);

        Throwable error = catchThrowable(() -> selector.selectLocation(board));

        assertThat(error).isInstanceOf(NoKeysRemainingException.class);
    }
}
