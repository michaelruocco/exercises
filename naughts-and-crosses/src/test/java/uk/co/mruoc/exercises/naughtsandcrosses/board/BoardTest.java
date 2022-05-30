package uk.co.mruoc.exercises.naughtsandcrosses.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
}
