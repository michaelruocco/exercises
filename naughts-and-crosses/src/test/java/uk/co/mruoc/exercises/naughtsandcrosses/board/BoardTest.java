package uk.co.mruoc.exercises.naughtsandcrosses.board;

import org.junit.jupiter.api.Test;
import uk.co.mruoc.exercises.naughtsandcrosses.locationselector.LocationSelector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoardTest {

    private static final String FREE = "-";
    private static final String CROSS = "X";
    private static final String NAUGHT = "O";

    private final Board board = new Board();

    @Test
    void shouldDefaultSizeToThree() {
        assertThat(board.getSize()).isEqualTo(3);
    }

    @Test
    void allTokensShouldBeFreeWhenCreated() {
        assertThat(board.getToken("1-1")).isEqualTo(FREE);
        assertThat(board.getToken("1-2")).isEqualTo(FREE);
        assertThat(board.getToken("1-3")).isEqualTo(FREE);
        assertThat(board.getToken("2-1")).isEqualTo(FREE);
        assertThat(board.getToken("2-2")).isEqualTo(FREE);
        assertThat(board.getToken("2-3")).isEqualTo(FREE);
        assertThat(board.getToken("3-1")).isEqualTo(FREE);
        assertThat(board.getToken("3-2")).isEqualTo(FREE);
        assertThat(board.getToken("3-3")).isEqualTo(FREE);
        assertThat(board.allLocationsFree()).isTrue();
    }

    @Test
    void wholeBoardShouldBeFreeWhenCreated() {
        assertThat(board.isFree("1-1")).isTrue();
        assertThat(board.isFree("1-2")).isTrue();
        assertThat(board.isFree("1-3")).isTrue();
        assertThat(board.isFree("2-1")).isTrue();
        assertThat(board.isFree("2-2")).isTrue();
        assertThat(board.isFree("2-3")).isTrue();
        assertThat(board.isFree("3-1")).isTrue();
        assertThat(board.isFree("3-2")).isTrue();
        assertThat(board.isFree("3-3")).isTrue();
        assertThat(board.allLocationsFree()).isTrue();
    }

    @Test
    void shouldNotHaveWinnerWhenBoardIsCreated() {
        assertThat(board.hasWinner(CROSS)).isFalse();
        assertThat(board.hasWinner(NAUGHT)).isFalse();
    }

    @Test
    void shouldPlaceToken() {
        String location = "1-1";

        board.placeToken(location, CROSS);

        String token = board.getToken(location);
        assertThat(token).isEqualTo(CROSS);
    }

    @Test
    void shouldThrowExceptionIfAttemptToPlaceTokenOnLocationThatIsAlreadyTaken() {
        String location = "1-1";
        board.placeToken(location, CROSS);

        Throwable error = catchThrowable(() -> board.placeToken(location, CROSS));

        assertThat(error)
                .isInstanceOf(LocationAlreadyTakenException.class)
                .hasMessageContaining(location);
    }

    @Test
    void shouldReturnNextFreeLocation() {
        assertThat(board.getNextFreeLocation()).isEqualTo("1-1");
        assertThat(board.getNextFreeLocation()).isEqualTo("1-1");

        board.placeToken("1-1", NAUGHT);
        assertThat(board.getNextFreeLocation()).isEqualTo("2-1");

        board.placeToken("2-1", NAUGHT);
        assertThat(board.getNextFreeLocation()).isEqualTo("3-1");

        board.placeToken("3-1", NAUGHT);
        assertThat(board.getNextFreeLocation()).isEqualTo("1-2");
    }

    @Test
    void shouldThrowExceptionIfNoFreeLocationsRemaining() {
        board.placeToken("1-1", CROSS);
        board.placeToken("1-2", NAUGHT);
        board.placeToken("1-3", CROSS);
        board.placeToken("2-1", NAUGHT);
        board.placeToken("2-2", CROSS);
        board.placeToken("2-3", NAUGHT);
        board.placeToken("3-1", CROSS);
        board.placeToken("3-2", NAUGHT);
        board.placeToken("3-3", CROSS);

        Throwable error = catchThrowable(board::getNextFreeLocation);

        assertThat(error).isInstanceOf(NoFreeLocationsRemainingException.class);
    }

    @Test
    void shouldResetBoard() {
        board.placeToken("1-1", CROSS);
        board.placeToken("1-2", NAUGHT);
        board.placeToken("1-3", CROSS);
        board.placeToken("2-1", NAUGHT);
        board.placeToken("2-2", CROSS);
        board.placeToken("2-3", NAUGHT);
        board.placeToken("3-1", CROSS);
        board.placeToken("3-2", NAUGHT);
        board.placeToken("3-3", CROSS);
        assertThat(board.isFull()).isTrue();

        board.reset();

        assertThat(board.allLocationsFree()).isTrue();
    }

    @Test
    void shouldReturnTokenAtCoordinates() {
        assertThat(board.getToken(1, 1)).isEqualTo(FREE);
        board.placeToken("1-1", NAUGHT);
        assertThat(board.getToken(1, 1)).isEqualTo(NAUGHT);

        assertThat(board.getToken(2, 2)).isEqualTo(FREE);
        board.placeToken("2-2", CROSS);
        assertThat(board.getToken(2, 2)).isEqualTo(CROSS);
    }

    @Test
    void shouldReturnLocationSelectedFromBoard() {
        LocationSelector selector = mock(LocationSelector.class);
        String expectedLocation = "expected-location";
        when(selector.selectLocation(board)).thenReturn(expectedLocation);

        String location = board.selectLocation(selector);

        assertThat(location).isEqualTo(expectedLocation);
    }

    @Test
    void shouldReturnRandomFreeLocations() {
        Collection<String> allLocations = Arrays.asList(
                "1-1", "1-2", "1-3",
                "2-1", "2-2", "2-3",
                "3-1", "3-2", "3-3");
        Collection<String> locations = new ArrayList<>(allLocations);
        int count = 0;
        while (!locations.isEmpty()) {
            String location = board.getRandomFreeLocation();
            board.placeToken(location, CROSS);
            assertThat(locations.remove(location)).isTrue();
            count++;
        }
        assertThat(count).isEqualTo(allLocations.size());
    }

    @Test
    void shouldReturnTrueWhenBoardContainsVerticalWinner() {
        String token = CROSS;
        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("1-1", token);

        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("1-2", token);

        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("1-3", token);

        assertThat(board.hasWinner(token)).isTrue();
    }

    @Test
    void shouldReturnTrueWhenBoardContainsHorizontalWinner() {
        String token = NAUGHT;
        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("1-1", token);

        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("2-1", token);

        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("3-1", token);

        assertThat(board.hasWinner(token)).isTrue();
    }

    @Test
    void shouldReturnTrueWhenBoardContainsForwardSlashWinner() {
        String token = CROSS;
        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("1-3", token);

        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("2-2", token);

        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("3-1", token);

        assertThat(board.hasWinner(token)).isTrue();
    }

    @Test
    void shouldReturnTrueWhenBoardContainsBackSlashWinner() {
        String token = NAUGHT;
        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("3-1", token);

        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("2-2", token);

        assertThat(board.hasWinner(token)).isFalse();
        board.placeToken("1-3", token);

        assertThat(board.hasWinner(token)).isTrue();
    }

    @Test
    void shouldThrowExceptionIfLocationDoesNotExist() {
        String location = "4-4";

        Throwable error = catchThrowable(() -> board.getToken(location));

        assertThat(error)
                .isInstanceOf(LocationDoesNotExistException.class)
                .hasMessage("location 4-4 does not exist");
    }
}
